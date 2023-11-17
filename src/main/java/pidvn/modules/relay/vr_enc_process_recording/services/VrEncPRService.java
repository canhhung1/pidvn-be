package pidvn.modules.relay.vr_enc_process_recording.services;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.entities.one.Process;
import pidvn.mappers.one.relay.vr_enc_process_recording.VrEncPRMapper;
import pidvn.modules.relay.material_control.MaterialExportUtils;
import pidvn.modules.relay.vr_enc_process_recording.models.*;
import pidvn.repositories.one.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VrEncPRService implements IVrEncPRService {

    @Autowired
    private LineRepo lineRepo;

    @Autowired
    private MaterialControlsRepo materialControlsRepo;

    @Autowired
    private ShiftsRepo shiftsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private VrEncPRMapper vrEncPRMapper;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Override
    public List<Line> getLines(Integer productId) {
        return this.lineRepo.findByProductIdOrderByName(productId);
    }

    @Override
    public List<Customer> getCustomers(Integer productId) {
        return this.customerRepo.findAll();
    }

    @Override
    public List<ProcessVo> getProcesses(String productTypeName) {
        return this.vrEncPRMapper.getProcesses(productTypeName);
    }

    @Override
    public List<ProcessVo> getProcessesVer2(String line) {
        return this.vrEncPRMapper.getProcessesVer2(line);
    }

    @Override
    public Map scanLabel(ScannerVo scannerVo) throws Exception {

        QaCardVo qaCardVo = this.parseQaCard(scannerVo.getQaCard());
        LabelVo labelVo = this.parseLabel(scannerVo.getLabel());

        MaterialVo material = new MaterialVo();

        material.setPpn(qaCardVo.getModel());
        material.setCpn(labelVo.getPartNo());
        material.setLine(qaCardVo.getLine());
        material.setDate(qaCardVo.getDate());
        material.setShift(qaCardVo.getShift());
        material.setPlotno(scannerVo.getQaCard());
        material.setClotno(labelVo.getLotNo());
        material.setQty(labelVo.getQty());
        material.setQtyOrigin(labelVo.getQty());
        material.setKeyUser(scannerVo.getUser());
        material.setRecordType(scannerVo.getType());
        material.setProcessId(scannerVo.getProcessId());
        material.setLabel(scannerVo.getLabel());

        Map result = null;

        if ("VEP".equals(material.getRecordType())){
            result = this.validateMaterial(material);
        }

        return result;
    }

    @Override
    public List<MaterialControls> insertMaterials(List<MaterialVo> materialVos) {

        /**
         * Tìm list LotNo
         */
        Map<String, MaterialVo> map = materialVos.stream().collect(Collectors.toMap(MaterialVo::getClotno, Function.identity()));
        List<Lots> lots = this.lotsRepo.findByLotNoIn(new ArrayList<>(map.keySet()));


        List<MaterialControls> insertList = new ArrayList<>();

        for (MaterialVo material: materialVos) {
            MaterialControls obj = new MaterialControls();
            obj.setPpn(material.getPpn());
            obj.setCpn(material.getCpn());
            obj.setLine(material.getLine());
            obj.setDate(new Date());
            obj.setShift(material.getShift());
            obj.setPlotno(material.getPlotno());
            obj.setClotno(material.getClotno());
            obj.setQty(material.getQty());
            obj.setFrBox(0);
            obj.setUser1(material.getKeyUser());
            obj.setKeyUser(material.getKeyUser());
            obj.setRecordType(material.getRecordType());
            obj.setProcessId(material.getProcessId());
            obj.setNgQty(0);
            obj.setRemark(material.getRemark());
            insertList.add(obj);
        }

        // Lưu dữ liệu vào material_controls
        List<MaterialControls> insertResult = this.materialControlsRepo.saveAll(insertList);


        /**
         * Update lại số lượng trong bảng lot
         */
        for (Lots item: lots) {
            MaterialVo obj = map.get(item.getLotNo());
            Float qty = item.getQty() - obj.getQty();
            item.setQty(qty);
        }

        this.lotsRepo.saveAll(lots);


        return insertResult;
    }

    @Override
    public MaterialControls updateMaterial(MaterialVo materialVo) throws Exception {
        /**
         * Logic mới: update Qty trong bảng lot
         *
         * Kiểm tra nếu qty truyền lên backend mà nhỏ hơn qty hiện tại thì cộng số lượng vào qty của lot và ngược lại
         *
         * TODO
         */
        Lots lot = this.lotsRepo.findByLotNo(materialVo.getClotno());
        MaterialControls material = this.materialControlsRepo.findById(materialVo.getId()).get();

        float clientQty = materialVo.getQty();
        float actualQty = material.getQty();
        float chenhLech = clientQty - actualQty;

        if (chenhLech < 0) {
            float lotQty = lot.getQty();
            lotQty+=(chenhLech*-1);

            if (lotQty < 0) {
                throw new Exception("Không thể Update Qty được vì chênh lệch < 0");
            }

            lot.setQty(lotQty);
            this.lotsRepo.save(lot);
        } else if (chenhLech > 0) {
            float lotQty = lot.getQty();
            lotQty-=chenhLech;
            lot.setQty(lotQty);

            if (lotQty < 0) {
                throw new Exception("Không thể Update Qty được vì chênh lệch < 0");
            }

            this.lotsRepo.save(lot);
        }

        material.setQty(materialVo.getQty());
        material.setRemark(materialVo.getRemark());
        return this.materialControlsRepo.save(material);
    }

    @Override
    public List<MaterialVo> getMaterials(SearchVo searchVo) {
        return this.vrEncPRMapper.getMaterials(searchVo);
    }

    @Override
    public List<PartVo> getPartsByModel(String model) {
        return this.vrEncPRMapper.getPartsByModel(model);
    }

    @Override
    public List<Shifts> getShifts() {
        return this.shiftsRepo.findAll();
    }

    @Override
    public List<QaCardVo> getQaCards(SearchVo searchVo) {
        return this.vrEncPRMapper.getQaCards(searchVo);
    }

    @Override
    public ByteArrayInputStream downloadQaCard(SearchVo searchVo) throws IOException {

        String qaCard = searchVo.getQaCard();

        String [] data = searchVo.getQaCard().split("\\*");
        String product = data[0];
        String line = data[1];
        String productType = data[1].split("-")[0];
        String date = data[2];
        String shift = data[3];

        String sourcePath = "";
        String targetPath = "";

        String tempName = "temp-" + new Random().nextInt(1000) + "xls" ;

        String rootFolder = "P:\\IS\\(C) Save File FDCS\\FDCS-Server-2\\VR-EMC-Process-Recording\\";

        if (productType.equals("11GS")) {
            sourcePath = rootFolder + "11GS.xls";
            targetPath = rootFolder + tempName;
        }else if (productType.equals("11G2")) {
            sourcePath = rootFolder + "11G2.xls";
            targetPath = rootFolder + tempName;
        } else if (productType.equals("GMT")) {
            sourcePath = rootFolder + "GMT.xls";
            targetPath = rootFolder + tempName;
        } else if(productType.equals("TEMP")) {
            sourcePath = rootFolder + "Temp.xls";
            targetPath = rootFolder + tempName;
        }


        String pathFile = MaterialExportUtils.createTempFile(sourcePath, targetPath);
        File file = new File(pathFile);
        FileInputStream inputStream = new FileInputStream(file);

        //Creating workbook from input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        //Reading first sheet of excel file
        Sheet sheet = workbook.getSheet("R0");

        Cell productTypeCell = sheet.getRow(7).getCell(2);
        productTypeCell.setCellValue(productType);

        Cell qaCardCell = sheet.getRow(51).getCell(1);
        qaCardCell.setCellValue(qaCard);

        Cell qrCodeCell = sheet.getRow(48).getCell(2);
        //qrCodeCell.setCellType(CellType.FORMULA);
        qrCodeCell.setCellFormula("IMAGE ( \"https://api.qrserver.com/v1/create-qr-code/?data=\" & B52 )");


        Cell dateCell = sheet.getRow(5).getCell(2);
        dateCell.setCellValue(date);

        Cell lineCell = sheet.getRow(8).getCell(2);
        lineCell.setCellValue(line);

        Cell shiftCell = sheet.getRow(9).getCell(2);
        shiftCell.setCellValue(shift);

        Cell productCell = sheet.getRow(11).getCell(2);
        productCell.setCellValue(product);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream result = new ByteArrayInputStream(out.toByteArray());

        // Close the workbook and output stream
        workbook.close();
        Files.deleteIfExists(Paths.get(pathFile));
        return result;

    }

    @Override
    public List<MaterialVo> traceability(SearchVo searchVo) {
        List<MaterialVo> result = this.vrEncPRMapper.traceability(searchVo);
        return result;
    }

    @Override
    public List<ModelVo> getModels() {
        return this.vrEncPRMapper.getModels();
    }

    @Override
    public Map createQaCard(QaCardVo qaCardVo) {

        Date date = qaCardVo.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        String qaCard = qaCardVo.getModel() + "*" + qaCardVo.getLine() + "*" + strDate + "*" +  qaCardVo.getShift() + "*001";

        // Kiểm tra qaCard đã tồn tại chưa

        Lots data = this.lotsRepo.findByLotNo(qaCard);

        Map result = new HashMap();

        if (data != null) {
            result.put("result","ERROR");
            result.put("message","QA card đã tồn tại");
            return result;
        }

        Lots lot = new Lots();
        lot.setLotNo(qaCard);
        lot.setModel(qaCardVo.getModel());
        lot.setLine(qaCardVo.getLine());
        lot.setShift(qaCardVo.getShift());
        lot.setDate(qaCardVo.getDate());
        lot.setPicCode(qaCardVo.getUserCode());
        lot.setUserCode(qaCardVo.getUserCode());
        lot.setQty(0F);
        lot.setType("Q");
        lot.setLabelType("QA");
        lot.setCustomerCode(String.join(";", qaCardVo.getCustomers()));

        Lots qa = this.lotsRepo.save(lot);

        result.put("result","OK");
        result.put("message", qa);

        return result;
    }


    private QaCardVo parseQaCard(String qaCard) throws ParseException {

        String data [] = qaCard.split("\\*");

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

        QaCardVo result = new QaCardVo();
        result.setModel(data[0]);
        result.setLine(data[1]);
        result.setDate(formatter.parse(data[2]));
        result.setShift(data[3]);

        return result;
    }

    private LabelVo parseLabel(String label) {

        String data [] = label.split(";");

        LabelVo result = new LabelVo();

        String partNo = data[0].equals("B") ? data[1] : data[0];
        String lotNo = data[0].equals("B") ? data[4] : data[3];
        Float qty = data[0].equals("B") ? Float.parseFloat(data[3]) : Float.parseFloat(data[2]);

        result.setPartNo(partNo);
        result.setLotNo(lotNo);
        result.setQty(qty);

        return result;
    }

    private Map validateMaterial(MaterialVo material) throws Exception {

        /**
         * Kiểm tra Qty sản xuất ra
         * Có 2 trường hợp:
         * TH1: VR-ENC sx và in tem
         * TH2: PIH sx và in tem
         */

        /**
         * Kiểm tra lot và check label_type = ('swb','contact_base','to_hop')
         * Nếu label_type không thuộc các loại trên thì lấy qty trong bảng defect_records
         * Ngược lại lấy qty trong bảng lots
         */

        Lots lot = this.lotsRepo.findByLotNo(material.getClotno());




        Map result = new HashMap();
        List<MaterialVo> materialHistories = this.vrEncPRMapper.getMaterialHistories(material.getClotno());

        // NVL chưa được nhập LINE
        if (materialHistories.size() == 0) {
            result.put("status", "OK");
            result.put("message", "Có thể nhập vào LINE");
            result.put("data", material);
            return result;
        }

        // Nếu NVL đã được nhập vào LINE
        // Kiểm tra NVL đã sử dụng hết hay chưa
        // Số lượng tồn
        float intQty = lot.getQty();
        if (intQty <= 0) {
            String message = "Lot: " + material.getClotno() + " đã được sử dụng hết !";
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", material);
            return result;
        }

        result.put("status", "OK");
        result.put("message", "Có thể nhập vào LINE");
        result.put("data", material);

        return result;
    }

    /**
     * Qty đã cho vào LINE
     *
     * @param records
     * @return
     */
    private float getActualQtyInLine(List<MaterialVo> records) {
        float actualQty = 0;

        for (MaterialVo item : records) {
            if ("VEP".equals(item.getRecordType())) {
                actualQty += item.getQty();
            }
        }
        return actualQty;
    }

}
