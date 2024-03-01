package pidvn.modules.pih.process_recording.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.Line;
import pidvn.entities.one.Lots;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.PsMaster;
import pidvn.mappers.one.pih.process_recording.PihProcessRecordingMapper;
import pidvn.modules.pih.process_recording.models.*;
import pidvn.repositories.one.LineRepo;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.MaterialControlsRepo;
import pidvn.repositories.one.PsMasterRepo;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PihProcessRecordingSvc implements IPihProcessRecordingSvc {

    @Autowired
    private LineRepo lineRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private PsMasterRepo psMasterRepo;

    @Autowired
    private MaterialControlsRepo materialControlsRepo;

    @Autowired
    private PihProcessRecordingMapper pihPRMapper;

    @Override
    public List<PsMaster> getPsMasters(String pnpa) {
        return this.psMasterRepo.findByPnpa(pnpa);
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager", rollbackFor = Exception.class)
    public Map insertCoil(ScannerVo scannerVo) throws Exception {

        MaterialControls materialUpdate = this.updateToBoxAndQtyMaterial(scannerVo);

        MaterialControls materialSave = this.addMaterial(scannerVo, materialUpdate);
        Map result = new HashMap();
        result.put("materialUpdate", materialUpdate);
        result.put("materialSave", materialSave);

        return result;
    }

    @Override
    public Map insertCoilManual(ScannerVo scannerVo) throws Exception {

        Lots lot = this.lotsRepo.findByLotNo(scannerVo.getNewCoil());

        if (lot == null) {
            throw new Exception("Lot: " + scannerVo.getNewCoil() + " chưa có trong hệ thống");
        }

        String [] label = scannerVo.getLabel().split("\\*");

        MaterialControls material = new MaterialControls();
        material.setRecordType("PIC");
        material.setPpn(label[1]);
        //material.setCpn(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);
        material.setCpn(lot.getModel());

        material.setLine(label[2]);
        material.setDate(new Date());
        //material.setPlotno(lot.getModel());
        material.setPlotno(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);

        material.setClotno(scannerVo.getNewCoil());
        material.setQty(new Float(0));
        material.setFrBox(scannerVo.getSequence());
        material.setToBox(null);
        material.setUser1(scannerVo.getUserId());
        material.setKeyUser(scannerVo.getUserId());
        material.setWindingBobbin(scannerVo.getPosition());
        material.setNgQty(0);
        material.setRemark("Test: consumption (change coil: qty=0)");

        MaterialControls materialSave = this.materialControlsRepo.save(material);

        Map result = new HashMap();
        result.put("materialSave", materialSave);

        return result;
    }


    @Override
    public List<MaterialVo> getMaterials(MaterialSearchVo searchVo) {
        return this.pihPRMapper.getMaterial(searchVo);
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager", rollbackFor = Exception.class)
    public Map changeLabel(ScannerVo scannerVo) throws Exception {

        String dataA [] = scannerVo.getOldLabel().split("\\*");
        String oldLabel = dataA[0] + "*" + dataA[1] + "*" + dataA[2] + "*" + dataA[3];
        Integer toBox = Integer.parseInt(dataA[4]);

        // B1. Lấy các lot đang sử dụng trong LINE
        List<MaterialControls> materials = this.getActualLots(oldLabel);

        // B2. Update toBox các lot đang sử dụng thuộc tem cũ
        for (MaterialControls material: materials) {
            material.setToBox(toBox);
        }
        List<MaterialControls> updateList = this.materialControlsRepo.saveAll(materials);

        // B3. Tạo mới các lot cho tem mới
        List<MaterialControls> createList = new ArrayList<>();

        String dataB [] = scannerVo.getNewLabel().split("\\*");
        String newLabel = dataB[0] + "*" + dataB[1] + "*" + dataB[2] + "*" + dataB[3];

        for (MaterialControls material: materials) {
            MaterialControls obj = new MaterialControls();
            obj.setPpn(material.getPpn());
            obj.setCpn(newLabel);
            obj.setLine(material.getLine());
            obj.setDate(new Date());
            obj.setPlotno(material.getPlotno());
            obj.setClotno(material.getClotno());
            obj.setQty(material.getQty());
            obj.setFrBox(1);
            obj.setToBox(null);
            obj.setUser1(material.getUser1());
            obj.setKeyUser(material.getKeyUser());
            obj.setWindingBobbin(material.getWindingBobbin());
            obj.setRecordType("PIC");
            obj.setRemark("Change Label (PIH)");
            createList.add(obj);
        }

        this.materialControlsRepo.saveAll(createList);

        Map result = new HashMap();
        result.put("updateList", updateList);
        result.put("createList", createList);

        return result;
    }

    @Override
    public List<MaterialControls> changeModel(ScannerVo scannerVo) {

        String [] data = scannerVo.getLabel().split("\\*");

        List<Lots> lots = new ArrayList<>();
        for (String lotNo: scannerVo.getLots()) {
            Lots lot = this.lotsRepo.findByLotNo(lotNo);
            lots.add(lot);
        }

        List<MaterialControls> materials = new ArrayList<>();

        int count = 1;
        for (Lots lot: lots) {
            MaterialControls material = new MaterialControls();
            material.setPpn(data[1]);
            material.setCpn(lot.getModel());
            material.setLine(data[2]);
            material.setDate(new Date());
            material.setPlotno(scannerVo.getLabel());
            material.setClotno(lot.getLotNo());
            material.setQty((float) 0);
            material.setFrBox(1);
            material.setUser1(scannerVo.getUserId());
            material.setKeyUser(scannerVo.getUserId());
            material.setWindingBobbin(count);
            material.setRecordType("PIC");
            material.setNgQty(0);

            materials.add(material);
            count++;
        }

        // Lưu material_controls
        List<MaterialControls> result = new ArrayList<>();
        for (MaterialControls item : materials) {
            List<MaterialControls> m = materialControlsRepo.findByPlotnoAndClotnoOrderByIdDesc(item.getPlotno(), item.getClotno());
            if (m.size() == 0) {
                MaterialControls obj = this.materialControlsRepo.save(item);
                result.add(obj);
            }else {
                m.get(0);
            }
        }

        /**
         * Update Qty and toBox
         * TODO
         */

         this.updateToBoxAndQtyWhenChangeTemp(scannerVo);



        return result;
    }

    /**
     *
     * @param scannerVo
     */
    private void updateToBoxAndQtyWhenChangeTemp(ScannerVo scannerVo) {

        String [] data = scannerVo.getLabel().split("\\*"); // VN240103*A-0408A*COIL7*B
        String line = data[2];
        DefectRecordVo defectRecord = this.pihPRMapper.getDefectRecords(line,null,1).get(0);

        String shift = defectRecord.getShift();
        Date date = defectRecord.getDate();
        Integer toBox = defectRecord.getSeqNo();

        /**
         * Tìm số bobbin chạy
         */
        MaterialSearchVo params1 = new MaterialSearchVo();
        params1.setLine(line);
        params1.setRecordType("PIC");
        params1.setPlotno(defectRecord.getLotGroup());
        List<MaterialVo> materials =  this.pihPRMapper.getMaterial(params1);


        // Các record cần update toBox
        List<Integer> ids = new ArrayList<>();
        materials.stream().filter(item -> item.getToBox() == null).forEach(a -> {
            ids.add(a.getId());
        });
        // Thực hiện update toBox
        for (Integer id : ids) {
            MaterialControls material = this.materialControlsRepo.findById(id).get();
            material.setToBox(toBox);
            this.materialControlsRepo.save(material);
        }

        // Số bobbin chạy
        int bobbinAmount = ids.size();
        String plotno = defectRecord.getLotGroup();

        List<LotVo> lotsQty = this.pihPRMapper.calculateQtyChangeLabel(bobbinAmount,plotno, ids);

        // Cập nhật số qty
        for (LotVo lot: lotsQty) {
            MaterialControls material = this.materialControlsRepo.findById(lot.getId()).get();
            material.setQty(lot.getQty());
            material.setRemark("Test: consumption (change label)");
            this.materialControlsRepo.save(material);
        }
    }

    @Override
    public List<Line> getLines(Integer productId) {
        return this.lineRepo.findByProductIdOrderByName(productId);
    }

    @Override
    public Map scanCoil(MaterialSearchVo searchVo) {

        Map result = new HashMap();
        String message = "";

        // Kiểm tra: nếu scan Coil cũ thì thông báo scan Coil mới
        List<MaterialControls> coil = this.materialControlsRepo.findByClotnoAndRecordType(searchVo.getClotno(), "PIC");
        if (coil.size() > 0) {
            message = "Lot : " + searchVo.getClotno() + " đã được nhập vào LINE lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(coil.get(0).getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            return result;
        }

        // Kiểm tra: nhầm NVL

        Lots lot = this.lotsRepo.findByLotNo(searchVo.getClotno());

        String pnpa = searchVo.getCpn().split("\\*")[1];
        List<PsMaster> psMasters = this.psMasterRepo.findByPnpa(pnpa);
        boolean isError = true;
        for (PsMaster ps: psMasters) {
            if (ps.getPncomp().equals(lot.getModel())) {
                isError = false;
                break;
            }
        }

        if (isError) {
            message = "Mã NVL : " + lot.getModel() + " không dùng cho mã " + pnpa;
            result.put("status", "ERROR");
            result.put("message", message);
            return result;
        }

        message = "Có thể nhập NVL";
        result.put("status", "OK");
        result.put("message", message);

        return result;
    }

    @Override
    public Map checkSetupSaiNVL(String model, String coil) {

        List<PsMaster> psMasters = this.psMasterRepo.findByPnpa(model);

        String modelCoil = this.lotsRepo.findByLotNo(coil).getModel();

        Map result1 = new HashMap();
        result1.put("result", "OK");

        for (PsMaster ps: psMasters) {
            if (ps.getPncomp().equals(modelCoil)) {
                return result1;
            }
        }

        Map result2 = new HashMap();
        result2.put("result", "NG");

        return result2;
    }

    /**
     * Lấy các lot thực tế đang sử dụng ở LINE
     * @param oldLabel
     * @return
     */
    private List<MaterialControls> getActualLots(String oldLabel) {
        MaterialSearchVo searchVo = new MaterialSearchVo();
        searchVo.setRecordType("PIC");
        searchVo.setCpn(oldLabel);
        List<MaterialVo> materials = this.pihPRMapper.getMaterial(searchVo);
        List<Integer> ids = new ArrayList<>();
        for (MaterialVo material : materials) {
            if (material.getToBox() == null) {
                ids.add(material.getId());
            }
        }

        List<MaterialControls> result = this.materialControlsRepo.findByIdIn(ids);

        return result;
    }

    private MaterialControls updateToBoxAndQtyMaterial(ScannerVo scannerVo) throws Exception {

        /**
         * Tìm Coil bị thay ra, cập nhật lại toBox
         * Mục đích để biết Coil được dùng từ tem số fromBox đến tem số toBox
         */
        MaterialSearchVo searchParams = new MaterialSearchVo();
        searchParams.setRecordType("PIC");
        searchParams.setClotno(scannerVo.getOldCoil());
        List<MaterialVo> materials = this.pihPRMapper.getMaterial(searchParams);

        if (materials.size() <= 0) {
            String msg = "Lot: " + scannerVo.getOldCoil() + " chưa được scan lúc nhập vào LINE";
            throw new Exception(msg);
        }

        MaterialVo material = materials.get(0);

        /**
         * Update toBox và Qty
         */

        // Lấy record cần update
        MaterialControls materialUpdate = this.materialControlsRepo.findById(material.getId()).get();

        // Mã model dây đồng
        String coilCode = lotsRepo.findByLotNo(materialUpdate.getClotno()).getModel();

        // Tìm fromBox, toBox
        Integer fromBox = materialUpdate.getFrBox();
        Integer toBox = scannerVo.getSequence();

        // Tìm số lượng bobbin
        MaterialSearchVo searchVo = new MaterialSearchVo();
        String [] label = scannerVo.getLabel().split("\\*");
        searchVo.setPlotno(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);
        List<MaterialVo> records = this.pihPRMapper.getMaterial(searchVo);
        int numberOfBobbin = (int) records.stream().filter(item -> item.getToBox() == null).count();

        // Lấy danh sách tem đã sử dụng
        Float qty = this.pihPRMapper.calculateQtyChangeCoil(
            (label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]), fromBox, toBox, numberOfBobbin, coilCode
        );

        materialUpdate.setQty(qty == null ? 0 : qty);
        materialUpdate.setToBox(toBox);
        materialUpdate.setRemark("Test: consumption (change coil: update toBox & qty)");
        return this.materialControlsRepo.save(materialUpdate);
    }

    private MaterialControls addMaterial(ScannerVo scannerVo, MaterialControls materialControls) {

        Lots lot = this.lotsRepo.findByLotNo(scannerVo.getNewCoil());

        String [] label = scannerVo.getLabel().split("\\*");

        MaterialControls material = new MaterialControls();
        material.setRecordType("PIC");
        material.setPpn(label[1]);
        material.setCpn(lot.getModel());
        material.setLine(label[2]);
        material.setDate(new Date());
        material.setPlotno(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);
        material.setClotno(scannerVo.getNewCoil());
        material.setQty((float) 0);
        material.setFrBox(scannerVo.getSequence());
        material.setToBox(null);
        material.setUser1(scannerVo.getUserId());
        material.setKeyUser(scannerVo.getUserId());
        material.setWindingBobbin(materialControls.getWindingBobbin());
        material.setNgQty(0);
        material.setRemark("Test: consumption (change coil: add new qty=0)");

        return this.materialControlsRepo.save(material);
    }
}
