package pidvn.modules.pih.pih_inventory.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.Lots;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.entities.one.ProductType;
import pidvn.mappers.one.pih.pih_inventory.PihInventoryMapper;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;
import pidvn.modules.spare_part.models.RowExcelErrorVo;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.PihInventoryDataRepo;
import pidvn.repositories.one.PihInventoryRequestRepo;
import pidvn.repositories.one.ProductTypeRepo;

import java.io.IOException;
import java.util.*;

@Service
public class PihInventorySvc implements IPihInventorySvc {

    Logger logger = LoggerFactory.getLogger(PihInventorySvc.class);

    @Autowired
    private PihInventoryRequestRepo pihInventoryRequestRepo;

    @Autowired
    private PihInventoryDataRepo pihInventoryDataRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Autowired
    private PihInventoryMapper pihInventoryMapper;

    @Override
    public List<ProductType> getInventoryArea() {
        List<Integer> productIds = Arrays.asList(4, 6);
        return this.productTypeRepo.findByProductIdIn(productIds);
    }

    @Override
    public List<PihInventoryRequest> getInventoryRequests() {
        return this.pihInventoryRequestRepo.findAllByOrderByIdDesc();
    }

    @Override
    public PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception {

        // TODO: nếu đã tồn tại request thì ko tạo mới

//        PihInventoryRequest request = this.pihInventoryRequestRepo.findByReqNo(ivtReq.getReqNo());
//
//        List<PihInventoryRequest> requests = this.pihInventoryRequestRepo.findByCurrentMonth();
//
//        if (requests.size() > 0) {
//            throw new Exception("Đã có phiếu kiểm kê tháng này");
//        }

        return this.pihInventoryRequestRepo.save(ivtReq);
    }

    @Override
    public Map saveListInventoryData(List<PihInventoryData> inventoryData, Integer requestId, Integer inventoryArea) {

        List<PihInventoryData> resultOK = new ArrayList<>();
        List<PihInventoryData> resultNG = new ArrayList<>();

        List<String> outerLotNo = new ArrayList<>();


        // Lưu tem nhỏ
        for (PihInventoryData item : inventoryData) {
            try {
                if (item.getOuterLotNo() == null) {
                    PihInventoryData ivtData = this.pihInventoryDataRepo.save(item);
                    resultOK.add(ivtData);
                }else {
                    outerLotNo.add(item.getOuterLotNo());
                }
            } catch (Exception e) {
                logger.debug(e.toString());
                resultNG.add(item);
            }
        }

        /**
         * Tìm tem nhỏ theo OuterLotNo
         * Sau đó lưu lại vào bảng pih_inventory_data
         */
        if (outerLotNo.size() > 0) {
            List<PihInventoryData> data = this.pihInventoryMapper.getLotNoByInOutLabel(requestId, inventoryArea, outerLotNo);

            for (PihInventoryData item : data) {
                try {
                    PihInventoryData ivtData = this.pihInventoryDataRepo.save(item);
                    resultOK.add(ivtData);
                }catch (Exception e) {
                    logger.debug(e.toString());
                    resultNG.add(item);
                }
            }
        }




        Map result = new HashMap();
        result.put("resultOK", resultOK);
        result.put("resultNG", resultNG);

        return result;
    }

    @Override
    public PihInventoryData saveInventoryData(PihInventoryData inventoryData) {
        return this.pihInventoryDataRepo.save(inventoryData);
    }

    @Override
    public List<InventoryVo> getInventoryDataByRequestId(Integer requestId) {
        PihInventoryRequest request = this.pihInventoryRequestRepo.findById(requestId).get();
        Date fromDate = request.getCalculateTheoryDataDate();
        Date toDate = request.getInventoryCloseDate();
        return this.pihInventoryMapper.getInventoryData(requestId, fromDate, toDate);
    }

    @Override
    public Lots scanLabel(String lotNo) throws Exception {
        Lots result = this.lotsRepo.findByLotNo(lotNo);
        if (result == null) {
            throw new Exception("Lot: " + lotNo + " không tồn tại");
        }
        return result;
    }

    /**
     * @param requestId
     * @param inventoryArea
     * @return
     */
    @Override
    public List<InventoryVo> balance(Integer requestId, List<Integer> inventoryArea) {

//        List<PihInventoryRequest> requests = this.pihInventoryRequestRepo.findAllByOrderByIdDesc(requestId);
//
//        Date dateKiTruoc = requests.get(1).getCreatedAt();
//
//        Date dateKiNay = requests.get(0).getCreatedAt();
//
//        Integer requestIdKyTruoc = requests.get(1).getId();

        List<InventoryVo> result = this.getBalance(requestId, inventoryArea);

        return result;
    }

    private List<InventoryVo> getBalance(Integer requestId, List<Integer> inventoryArea) {

        /**
         * Tính lượng data theo lý thuyết
         * Data lý thuyết = tồn lý thuyết tháng trước + data in/out (từ ngày theoryDate -> ngày ivtDate)
         */
        // B1: lấy thông tin request hiện tại
        PihInventoryRequest request = this.pihInventoryRequestRepo.findById(requestId).get();

        // Ngày kiểm kê
        Date ivtDate = request.getCreatedAt();

        // Ngày tính toán dữ liệu theo lý thuyết
        Date theoryDate = request.getCalculateTheoryDataDate();

        // Ngày chốt kiểm kê
        Date ivtCloseDate = request.getInventoryCloseDate();


        /**
         * Tìm tồn theo lý thuyết
         */
        List<PihInventoryRequest> listRequest = this.pihInventoryRequestRepo.findDataBeforeMonth(ivtDate, 1);
        PihInventoryRequest req = listRequest.get(0);
        int requestIdKyTruoc = req.getId();


        List<InventoryVo> result = this.pihInventoryMapper.balance(requestId, theoryDate, ivtDate, requestIdKyTruoc, inventoryArea);


        return result;
    }


    @Override
    public Optional<PihInventoryRequest> getInventoryRequest(Integer requestId) {

        return this.pihInventoryRequestRepo.findById(requestId);
    }

    @Override
    public Map uploadRawMaterialInventoryData(MultipartFile file, Integer requestId) {

        // Xóa tất cả các record cũ trong ngày với type = "RawMaterial"
        List<PihInventoryData> ivtData = this.pihInventoryDataRepo.findAllByRequestIdAndRecordTypeAndDate(requestId, "RawMaterial", new Date());

        this.pihInventoryDataRepo.deleteAll(ivtData);


        // Đọc dữ liệu từ excel và insert vào database
        Map result = this.readRawMaterialInventoryExcel(file, requestId);

        List<PihInventoryData> data = (List<PihInventoryData>) result.get("data");

        this.pihInventoryDataRepo.saveAll(data);

        return result;
    }



    private Map readRawMaterialInventoryExcel(MultipartFile file, Integer requestId) {



        XSSFWorkbook workbook = null;

        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();

        int START = 6;
        int PART_NO = 1;
        int QTY = 2;

        List<PihInventoryData> data = new ArrayList<>();
        List<RowExcelErrorVo> rowNG = new ArrayList<>();

        for (int i = START; i <= lastRowNum; i++) {
            PihInventoryData obj = new PihInventoryData();

            try {
                XSSFRow row = sheet.getRow(i);

                obj.setRequestId(requestId);
                obj.setPartNo(row.getCell(PART_NO).getStringCellValue());
                obj.setQty((float) row.getCell(QTY).getNumericCellValue());
                obj.setDate(new Date());
                obj.setRecordType("RawMaterial");
                data.add(obj);

            }catch (Exception e) {
                Integer rowNum = i + 1;
                RowExcelErrorVo item = new RowExcelErrorVo(rowNum, e.toString());
                rowNG.add(item);
            }
        }


        Map result = new HashMap();

        result.put("data", data);
        result.put("error", rowNG);

        return result;
    }

    @Override
    public List<InventoryVo> getInventoryRawMaterialData(Integer requestId) {

        PihInventoryRequest req = this.pihInventoryRequestRepo.findById(requestId).get();

        Date fromDate = req.getCalculateTheoryDataDate();
        Date toDate = req.getInventoryCloseDate();

        List<InventoryVo> data = this.pihInventoryMapper.getInventoryRawMaterialData(requestId, fromDate, toDate);
        return data;
    }





}
