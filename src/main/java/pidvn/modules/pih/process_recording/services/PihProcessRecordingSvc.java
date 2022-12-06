package pidvn.modules.pih.process_recording.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.Line;
import pidvn.entities.one.Lots;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.PsMaster;
import pidvn.mappers.one.pih.process_recording.PihProcessRecordingMapper;
import pidvn.modules.pih.process_recording.models.MaterialSearchVo;
import pidvn.modules.pih.process_recording.models.MaterialVo;
import pidvn.modules.pih.process_recording.models.ScannerVo;
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

        MaterialControls materialUpdate = this.updateToBoxMaterial(scannerVo);

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
        material.setCpn(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);
        material.setLine(label[2]);
        material.setDate(new Date());
        material.setPlotno(lot.getModel());
        material.setClotno(scannerVo.getNewCoil());
        material.setQty(lot.getQty());
        material.setFrBox(scannerVo.getSequence());
        material.setToBox(null);
        material.setUser1(scannerVo.getUserId());
        material.setKeyUser(scannerVo.getUserId());
        material.setWindingBobbin(scannerVo.getPosition());
        material.setNgQty(0);

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

        //List<Lots> lots = this.lotsRepo.findByLotNoIn(scannerVo.getLots());

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
            material.setCpn(scannerVo.getLabel());
            material.setLine(data[2]);
            material.setDate(new Date());
            material.setPlotno(lot.getModel());
            material.setClotno(lot.getLotNo());
            material.setQty(lot.getQty());
            material.setFrBox(1);
            material.setUser1(scannerVo.getUserId());
            material.setKeyUser(scannerVo.getUserId());
            material.setWindingBobbin(count);
            material.setRecordType("PIC");
            material.setNgQty(0);

            materials.add(material);
            count++;
        }

        return this.materialControlsRepo.saveAll(materials);
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

    private MaterialControls updateToBoxMaterial(ScannerVo scannerVo) throws Exception {

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

        // Update
        MaterialControls materialUpdate = this.materialControlsRepo.findById(material.getId()).get();

        // Tìm số sequence (ToBox)
        materialUpdate.setToBox(scannerVo.getSequence());
        // materialUpdate.setQty(scannerVo.getQty().floatValue());

        return this.materialControlsRepo.save(materialUpdate);
    }

    private MaterialControls addMaterial(ScannerVo scannerVo, MaterialControls materialControls) {

        Lots lot = this.lotsRepo.findByLotNo(scannerVo.getNewCoil());

        String [] label = scannerVo.getLabel().split("\\*");

        MaterialControls material = new MaterialControls();
        material.setRecordType("PIC");
        material.setPpn(label[1]);
        material.setCpn(label[0] + "*" + label[1] + "*" + label[2] + "*" +label[3]);
        material.setLine(label[2]);
        material.setDate(new Date());
        material.setPlotno(lot.getModel());
        material.setClotno(scannerVo.getNewCoil());
        material.setQty(lot.getQty());
        material.setFrBox(scannerVo.getSequence());
        material.setToBox(null);
        material.setUser1(scannerVo.getUserId());
        material.setKeyUser(scannerVo.getUserId());
        material.setWindingBobbin(materialControls.getWindingBobbin());
        material.setNgQty(0);

        return this.materialControlsRepo.save(material);
    }
}
