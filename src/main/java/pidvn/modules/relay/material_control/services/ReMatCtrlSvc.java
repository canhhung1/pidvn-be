package pidvn.modules.relay.material_control.services;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.entities.one.Process;
import pidvn.mappers.one.relay.material_control.ReMatCtrlMapper;
import pidvn.modules.relay.material_control.MaterialExportUtils;
import pidvn.modules.relay.material_control.models.*;
import pidvn.modules.relay.material_management.models.PurWhRecordVo;
import pidvn.repositories.one.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReMatCtrlSvc implements IReMatCtrlSvc {

    @Autowired
    private LineRepo lineRepo;

    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private MaterialControlsRepo materialControlsRepo;

    @Autowired
    private TmpAdrTnmu010Repo tmpAdrTnmu010Repo;

    @Autowired
    private ReMatCtrlMapper reMatCtrlMapper;

    @Autowired
    private PurWhHeadersRepo purWhHeadersRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<Line> getLines(Integer productId) {
        return this.lineRepo.findByProductIdOrderByName(productId);
    }

    @Override
    public List<Process> getProcesses(Integer productId) {
        return this.processRepo.findByProductId(productId);
    }

    @Override
    public List<PartVo> getPartsByModel(String model) {
        return this.reMatCtrlMapper.getPartsByModel(model);
    }

    @Override
    public List<SlipVo> getSlips() {
        return this.reMatCtrlMapper.getSlips();
    }

    @Override
    public List<SlipVo> getSlipsRelayReturnWarehouse() {
        return this.reMatCtrlMapper.getSlipsRelayReturnWarehouse();
    }

    @Override
    public Map getMaterialsBySlipNo(String slipNo) {
        List<PurWhRecordVo> parts = this.reMatCtrlMapper.getMaterialsBySlipNo(slipNo, "part_no");
        List<PurWhRecordVo> lotGroups = this.reMatCtrlMapper.getMaterialsBySlipNo(slipNo, "part_no,lot_group");
        List<PurWhRecordVo> lots = this.reMatCtrlMapper.getMaterialsBySlipNo(slipNo, null);

        Map result = new HashMap();

        result.put("parts", parts);
        result.put("lotGroups", lotGroups);
        result.put("lots", lots);

        return result;
    }

    @Override
    public Map scanMaterial(MaterialVo materialVo) {

        Map result = null;

        String action = materialVo.getRecordType();

        if (action.equals("RNP")) {
            // Kho trung chuyển (RE-WH) nhận nguyên vật liệu từ (PUR-WH)
            result = this.reWhReceivePurWh(materialVo);
        } else if (action.equals("RDC")) {
            // Kho trung chuyển (RE-WH) kitting lên xe NVL (LINE-WH)
            result = this.reWhSendLineWh(materialVo);
        } else if (action.equals("CDL")) {
            // Đổ NVL vào LINE
            result = this.lineWhSendLine(materialVo);
        } else if (action.equals("CTR")) {
            // LINE trả NVL cho RE-WH
            result = this.reWhReceiveLineWh(materialVo);
        } else if (action.equals("MRTW")) {
            result = this.reWhSendPwh(materialVo);
        }

        return result;
    }


    /**
     * Kho RE-WH nhận NVL từ PUR-WH
     */
    private Map reWhReceivePurWh(MaterialVo materialVo) {
        Map result = new HashMap();
        String message = "";

        String[] recordTypes = new String[]{"RNP"};
        List<MaterialVo> materialHistories = this.reMatCtrlMapper.getMaterialHistories(materialVo.getLotNo(), Arrays.asList(recordTypes));

        if (materialHistories.size() > 0) {

            MaterialVo recordLatest = materialHistories.get(0);

            if (materialVo.getRecordType().equals("RNP") && materialVo.getReqNo().equals(recordLatest.getReqNo())) {
                message = "Lot: " + materialVo.getLotNo() + " đã nhập kho trung chuyển (RE-WH) lúc: " +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            } else if (materialVo.getRecordType().equals("RNP") && !materialVo.getReqNo().equals(recordLatest.getReqNo())) {

                message = "Có thể nhập NVL vào kho trung chuyển (RE-WH)";
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            }

            if (materialVo.getRecordType().equals("CTR")) {
                message = "Lot: " + materialVo.getLotNo() + " đã nhập kho trung chuyển (RE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            }
            if (recordLatest.getRecordType().equals("RDC") || recordLatest.getRecordType().equals("LTC")) {
                message = "Lot: " + materialVo.getLotNo() + " đã nhập vào xe NVL (LINE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                return result;
            }
            if ("CDL".equals(recordLatest.getRecordType())) {
                message = "Lot: " + materialVo.getLotNo() + " đã nhập Line (LINE)";
                result.put("status", "ERROR");
                result.put("message", message);
                return result;
            }
        }

        message = "Có thể nhập NVL vào kho trung chuyển (RE-WH)";
        result.put("status", "OK");
        result.put("message", message);
        result.put("data", materialVo);
        return result;
    }

    /**
     * Kho RE-WH chuyển NVL vào LINE-WH
     */
    private Map reWhSendLineWh(MaterialVo materialVo) {
        Map result = new HashMap();
        String message = "";

        String[] recordTypes = new String[]{"RNP", "RDC", "CDL", "LTC", "CTR", "MRTW"};
        List<MaterialVo> materialHistories = this.reMatCtrlMapper.getMaterialHistories(materialVo.getLotNo(), Arrays.asList(recordTypes));

        if (materialHistories.size() <= 0) {
            message = "Lot: " + materialVo.getLotNo() + " chưa được nhập kho RE-WH ";
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        MaterialVo recordLatest = materialHistories.get(0);

        if (recordLatest.getRecordType().equals("RDC")) {
            message = "Lot: " + materialVo.getLotNo() + " đã nhập vào xe NVL (LINE-WH) lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt())
                    + "; QA Card: " + recordLatest.getQaCard();
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        } else if (recordLatest.getRecordType().equals("CDL")) {
            message = "Lot: " + materialVo.getLotNo() + " đã nhập vào LINE lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }
        else if (recordLatest.getRecordType().equals("MRTW")) {
            message = "Lot: " + materialVo.getLotNo() + " đã trả về kho (PUR-WH) lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        else if (recordLatest.getRecordType().equals("CTR")) {
            // TH này NVL đã được trả từ Line ra Kho trung chuyển
            // nên có thể tiếp tục nhập vảo QA card

            message = "Có thể nhập NVL vào xe NVL (LINE-WH)";
            result.put("status","OK");
            result.put("message",message);
            materialVo.setOriginQty(materialVo.getQty());
            materialVo.setQty(recordLatest.getQty());
            result.put("data", materialVo);
            return result;
        }

        else if (recordLatest.getRecordType().equals("RNP")) {
            // NVL vừa được nhập kho RE-WH nên có thể nhập vào xe NVL (LINE-WH)
            message = "Có thể nhập NVL vào xe NVL (LINE-WH)";
            result.put("status", "OK");
            result.put("message", message);
            materialVo.setOriginQty(materialVo.getQty());
            materialVo.setQty(recordLatest.getQty());
            result.put("data", materialVo);
            return result;
        }

        message = "Có thể nhập NVL vào xe NVL (LINE-WH)";
        result.put("status", "OK");
        result.put("message", message);
        result.put("data", materialVo);
        return result;
    }

    /**
     * Đổ NVL vào LINE
     *
     * @param materialVo
     * @return
     */
    private Map  lineWhSendLine(MaterialVo materialVo) {
        Map result = new HashMap();
        String message = "";
        String[] recordTypes = new String[]{"RNP", "RDC", "CDL", "LTC", "CTR", "MRTW"};
        List<MaterialVo> materialHistories = this.reMatCtrlMapper.getMaterialHistories(materialVo.getLotNo(), Arrays.asList(recordTypes));

        if (materialHistories.size() <= 0) {
            message = "Lot: " + materialVo.getLotNo() + " chưa được nhập kho RE-WH ";
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        MaterialVo recordLatest = materialHistories.get(0);

        if (recordLatest.getRecordType().equals("CDL")) {

            /**
             * Kiểm tra lượng nhập vào: Nếu đã nhập đủ số lượng trên tem thì ko đc nhập nữa
             */
            float lineQty = this.getActualQtyInLine(materialHistories);
            if (lineQty >= materialVo.getQty()) {
                message = "Lot: " + materialVo.getLotNo() + " đã được nhập hết vào LINE: ";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            }
        }

        if (recordLatest.getRecordType().equals("CTR")) {
            message = "Lot: " + materialVo.getLotNo() + " đã trả về kho trung chuyển (RE-WH); lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        if (recordLatest.getRecordType().equals("MRTW")) {
            message = "Lot: " + materialVo.getLotNo() + " đã trả về kho (PUR-WH); lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        message = "Có thể nhập NVL vào LINE";
        result.put("status", "OK");
        result.put("message", message);
        result.put("data", materialVo);
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
            if ("CDL".equals(item.getRecordType())) {
                actualQty += item.getQty();
            }
        }
        return actualQty;
    }

    /**
     * Nhận NVL từ LINE trả về RE-WH
     *
     * @param materialVo
     * @return
     */
    private Map reWhReceiveLineWh(MaterialVo materialVo) {

        Map result = new HashMap();
        String message = "";
        String[] recordTypes = new String[]{"RNP", "RDC", "CDL", "LTC", "CTR", "MRTW"};
        List<MaterialVo> materialHistories = this.reMatCtrlMapper.getMaterialHistories(materialVo.getLotNo(), Arrays.asList(recordTypes));

        if (materialHistories.size() == 0) {
            message = "Lot: " + materialVo.getLotNo() + " chưa được nhập vào kho trung chuyển (RE-WH)";
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        MaterialVo recordLatest = materialHistories.get(0);

        if (recordLatest.getRecordType().equals("RNP")) {
            message = "Lot: " + materialVo.getLotNo() + " đã nhập kho trung chuyển (RE-WH) lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        } else if (recordLatest.getRecordType().equals("CTR")) {
            message = "Lot: " + materialVo.getLotNo() + " đã trả về kho trung chyển (RE-WH) lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }
//        else if (recordLatest.getRecordType().equals("RDC")) {
//            // Đang ở xe nên có thể nhận lại nguyên vật liệu
//            message = "Có thể nhập NVL vào kho trung chuyển (RE-WH)";
//            result.put("status", "OK");
//            result.put("message", message);
//            result.put("data", materialVo);
//            return result;
//        }
        else if (recordLatest.getRecordType().equals("CDL")) {
            if (recordLatest.getQty().floatValue() == materialVo.getQty().floatValue()) {
                message = "Lot: " + materialVo.getLotNo() + " đã nhập vào LINE lúc "
                        + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            }
        }
        // Đang ở xe nên có thể nhận lại nguyên vật liệu
        message = "Có thể nhập NVL vào kho trung chuyển (RE-WH)";
        result.put("status", "OK");
        result.put("message", message);
        result.put("data", materialVo);
        return result;
    }

    private Map reWhSendPwh(MaterialVo materialVo) {
        Map result = new HashMap();
        String message = "";
        String[] recordTypes = new String[]{"RNP", "RDC", "CDL", "LTC", "CTR", "MRTW"};
        List<MaterialVo> materialHistories = this.reMatCtrlMapper.getMaterialHistories(materialVo.getLotNo(), Arrays.asList(recordTypes));

        if (materialHistories.size() == 0) {
            message = "Lot: " + materialVo.getLotNo() + " chưa được nhập vào kho trung chuyển (RE-WH)";
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        MaterialVo recordLatest = materialHistories.get(0);

        // TH đã nhập hết vào LINE
        if (recordLatest.getRecordType().equals("CDL")) {
            if (recordLatest.getQty().floatValue() == materialVo.getQty().floatValue()) {
                message = "Lot: " + materialVo.getLotNo() + " đã được nhập vào LINE lúc: "
                        + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", materialVo);
                return result;
            }
        }

        if (recordLatest.getRecordType().equals("MRTW")) {
            message = "Lot: " + materialVo.getLotNo() + " đã trả về kho (PUR-WH) lúc: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recordLatest.getCreatedAt());
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        // TH: RecordType = "RNP", "CTL (dư)","CTR"
        message = "Có thể trả lại kho (PUR-WH)";
        result.put("status", "OK");
        result.put("message", message);
        result.put("data", materialVo);
        return result;

    }

    @Override
    public Map saveMaterials(List<MaterialVo> materialVos) {

        List<PurWhRecords> purWhRecords = new ArrayList<>();
        List<MaterialControls> materialControls = new ArrayList<>();

        for (MaterialVo material : materialVos) {
            if (material.getRecordType().equals("RNP") || material.getRecordType().equals("RDC") ||
                    material.getRecordType().equals("CTR") || material.getRecordType().equals("MRTW")) {

                PurWhRecords purWhRecord = new PurWhRecords();
                purWhRecord.setLotNo(material.getLotNo());
                purWhRecord.setRecordType(material.getRecordType());
                purWhRecord.setQty(material.getQty());
                purWhRecord.setModel(material.getPartNo());
                purWhRecord.setParent(material.getModel());
                purWhRecord.setDate(material.getDate());
                purWhRecord.setLine(material.getLine());
                purWhRecord.setShift(material.getShift());
                purWhRecord.setWhUserCode(material.getWhUserCode());
                purWhRecord.setSender(material.getSender());
                purWhRecord.setReceiver(material.getReceiver());
                purWhRecord.setReqNo(material.getReqNo());
                purWhRecord.setQaCard(material.getQaCard());
                purWhRecord.setFlag("5");
                if (material.getRecordType().equals("MRTW")) {
                    purWhRecord.setFlag("1");
                    purWhRecord.setSlipNo(material.getSlipNo());
                }

                // purWhRecord.setRemark("Hung Test NVL");
                purWhRecords.add(purWhRecord);
            } else if (material.getRecordType().equals("CDL") || material.getRecordType().equals("LTC")) {
                MaterialControls materialControl = new MaterialControls();
                materialControl.setPpn(material.getModel());
                materialControl.setCpn(material.getPartNo());
                materialControl.setLine(material.getLine());
                materialControl.setDate(material.getDate());
                materialControl.setShift(material.getShift());
                materialControl.setPlotno(material.getQaCard());
                materialControl.setClotno(material.getLotNo());
                materialControl.setQty(material.getQty());
                materialControl.setFrBox(0);
                materialControl.setUser1(material.getSender());
                materialControl.setUser2(material.getSender());
                materialControl.setKeyUser(material.getSender());
                materialControl.setNgQty(0);
                materialControl.setRecordType(material.getRecordType());
                materialControl.setProcessId(material.getProcessId());
                // materialControl.setRemark("Hung Test NVL");
                materialControls.add(materialControl);
            }
        }

        Map result = new HashMap();

        if (purWhRecords.size() > 0) {
            result.put("data", this.purWhRecordsRepo.saveAll(purWhRecords));
        }
        if (materialControls.size() > 0) {
            result.put("data", this.materialControlsRepo.saveAll(materialControls));
        }

        return result;
    }

    @Override
    public MaterialControls mappingQaCard(MaterialControls materialControl) throws ParseException {

        String ppn = materialControl.getPlotno().split("\\*")[0];
        String cpn = materialControl.getClotno().split("\\*")[0];
        String line = materialControl.getPlotno().split("\\*")[1];
        String date = materialControl.getPlotno().split("\\*")[2];

        materialControl.setPpn(ppn);
        materialControl.setCpn(cpn);
        materialControl.setLine(line);
        materialControl.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        materialControl.setFrBox(0);
        materialControl.setUser1("3012982");
        materialControl.setKeyUser("3012982");
        materialControl.setNgQty(0);
        materialControl.setRecordType("IQ");

        return this.materialControlsRepo.save(materialControl);
    }

    @Override
    public List<MaterialControls> getChildQaCards(String parentQaCard) {
        List<MaterialControls> results = this.materialControlsRepo.findByPlotnoAndRecordTypeOrderByIdDesc(parentQaCard, "IQ");
        return results;
    }

    @Override
    public Integer deleteChildQaCard(Integer id) {
        this.materialControlsRepo.deleteById(id);
        return id;
    }

    @Override
    public List<MaterialVo> getMaterials(MaterialSearchVo searchVo) {
        return this.reMatCtrlMapper.getMaterials(searchVo);
    }

    @Override
    public List<MaterialVo> materialTraceability(MaterialSearchVo searchVo) {
        return this.reMatCtrlMapper.materialTraceability(searchVo);
    }

    @Override
    public Map changeQaCard(String oldQaCard, String newQaCard) throws ParseException {

        List<PurWhRecords> listUpdate = new ArrayList<>();
        List<PurWhRecords> listCreate = new ArrayList<>();

        // 1. Tìm tất cả các lot đã được nhập vào OldQaCard
        List<MaterialVo> dataChange = this.reMatCtrlMapper.getDataChangeQrCard(oldQaCard);

        // 2. Nếu lot đã được nhập hết thì bỏ qua
        for (MaterialVo item : dataChange) {
            // TH: đã đổ hết vào line (tồn = 0)
            if (item.getActualQty() <= 0) {
                continue;
            }

            // TH: Lot còn nguyên chưa đươc đổ vào LINE
            if (item.getLineQty() == 0) {
                PurWhRecords record = this.purWhRecordsRepo.findById(item.getId()).get();
                String[] qaCardInfo = newQaCard.split("\\*");
                record.setQaCard(newQaCard);
                record.setParent(qaCardInfo[0]);
                record.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(qaCardInfo[2]));
                record.setLine(qaCardInfo[1]);
                record.setShift(qaCardInfo[3]);
                listUpdate.add(record);
            }

            // TH: Lot đã được đổ vào LINE 1 phần (chưa đổ hết)
            if (item.getOriginQty().floatValue() > item.getLineQty().floatValue() && item.getLineQty().floatValue() != 0) {
                String[] qaCardInfo = newQaCard.split("\\*");
                /**
                 * Update lại số lượng ở QA Card cũ
                 * và qty = số lượng đã input vào LINE
                 */
                PurWhRecords record1 = this.purWhRecordsRepo.findById(item.getId()).get();
                record1.setQty(item.getLineQty());
                listUpdate.add(record1);

                /**
                 * Tạo mới bản ghi ở QA Card mới
                 * và qty = số lượng tồn
                 */
                PurWhRecords record2 = new PurWhRecords();
                record2.setQaCard(newQaCard);
                record2.setParent(qaCardInfo[0]);
                record2.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(qaCardInfo[2]));
                record2.setLine(qaCardInfo[1]);
                record2.setShift(qaCardInfo[3]);
                record2.setLotNo(item.getLotNo());
                record2.setQty(item.getActualQty());
                record2.setRecordType("RDC");
                ;
                record2.setModel(record1.getModel());
                record2.setWhUserCode(record1.getWhUserCode());
                record2.setFlag("1");
                listCreate.add(record2);

            }
        }
        // 3.

        List<PurWhRecords> resultUpdate = this.purWhRecordsRepo.saveAll(listUpdate);
        List<PurWhRecords> resultCreate = this.purWhRecordsRepo.saveAll(listCreate);

        Map result = new HashMap();
        result.put("resultUpdate", resultUpdate);
        result.put("resultCreate", resultCreate);

        return result;
    }

    @Override
    public Integer deleteMaterial(MaterialVo materialVo) {
        if (materialVo.getRecordType().equals("CDL") || materialVo.getRecordType().equals("LTC")) {
            this.materialControlsRepo.deleteById(materialVo.getId());
            return materialVo.getId();
        }

        return null;
    }

    @Override
    public MaterialControls editMaterialControl(MaterialVo materialVo) {
        MaterialControls material = this.materialControlsRepo.findById(materialVo.getId()).get();
        material.setQty(materialVo.getQty());
        return this.materialControlsRepo.save(material);
    }

    @Override
    public PurWhRecords savePurWhRecords(PurWhRecords purWhRecords) {

        if (purWhRecords.getId() != null) {
            PurWhRecords record = this.purWhRecordsRepo.findById(purWhRecords.getId()).get();
            record.setQty(purWhRecords.getQty());
            return this.purWhRecordsRepo.save(record);
        }

        return this.purWhRecordsRepo.save(purWhRecords);
    }

    @Override
    public String generateSlipNo(String recordType, String username) throws ParseException {
        Users user = this.usersRepo.findByUsername(username);
        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        List<PurWhHeaders> headers = this.purWhHeadersRepo.findByTypeAndDate(recordType,  new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
        Integer size = headers.size();
        Integer count = 1;

        StringBuilder slipNo = new StringBuilder("MRTW");
        slipNo.append(strDate).append("-").append(count+size);

        PurWhHeaders obj = new PurWhHeaders();
        obj.setRegNo(slipNo.toString());
        obj.setDate(new Date());
        obj.setActualDate(new Date());
        obj.setSerial(count+size);
        obj.setType(recordType);
        obj.setUserId(user.getId());
        this.purWhHeadersRepo.save(obj);

        return slipNo.toString();
    }

    @Override
    public List<QaCardVo> getQaCard() {
        return this.reMatCtrlMapper.getQaCard();
    }

    @Override
    public Map getSlipRelayReturnWarehouseDetail(String slipNo) {
        List<PurWhRecordVo> parts = this.reMatCtrlMapper.getSlipRelayReturnWarehouseDetail(slipNo, "part_no");
        List<PurWhRecordVo> lotGroups = this.reMatCtrlMapper.getSlipRelayReturnWarehouseDetail(slipNo, "part_no,lot_group");
        List<PurWhRecordVo> lots = this.reMatCtrlMapper.getSlipRelayReturnWarehouseDetail(slipNo, null);

        Map result = new HashMap();

        result.put("parts", parts);
        result.put("lotGroups", lotGroups);
        result.put("lots", lots);
        return result;
    }

    @Override
    public ByteArrayInputStream exportMaterialData(MaterialSearchVo searchVo) throws IOException {

        ByteArrayInputStream result = null;

        switch (searchVo.getRecordType()) {
            case "CDL":
                result = this.exportMaterialInLine(searchVo);
                break;
            case "RNP":

        }

        return result;


    }

    @Override
    public MaterialControls editQtyImportedToLine(MaterialVo materialVo) {
        MaterialControls data = this.materialControlsRepo.findById(materialVo.getId()).get();
        data.setQty(materialVo.getQty());
        return this.materialControlsRepo.save(data);
    }

    @Override
    public PurWhRecords deletePurWhRecordById(Integer id) {
        PurWhRecords obj = this.purWhRecordsRepo.findById(id).get();
        this.purWhRecordsRepo.deleteById(id);
        return obj;
    }

    private ByteArrayInputStream exportMaterialInLine(MaterialSearchVo searchVo) throws IOException {
        List<MaterialExport> data = this.reMatCtrlMapper.exportMaterialData(searchVo);

        String sourcePath = "Y:\\Public\\CanhHung\\Project\\MaterialControl\\MaterialInLine.xlsx";
        String targetPath = "Y:\\Public\\CanhHung\\Project\\MaterialControl\\MaterialInLineTemp.xlsx";

        String pathFile = MaterialExportUtils.createTempFile(sourcePath, targetPath);

        File file = new File(pathFile);
        FileInputStream inputStream = new FileInputStream(file);

        //Creating workbook from input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        //Reading first sheet of excel file
        Sheet sheet = workbook.getSheetAt(0);

        int rowNum = 2;



        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("YYYY-MM-dd HH:mm:ss"));

        for (MaterialExport item: data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getRowNum());
            row.createCell(1).setCellValue(item.getQaCardLine());
            row.createCell(2).setCellValue(item.getQaCardShift());
            row.createCell(3).setCellValue(item.getModel());

            Cell createAt = row.createCell(4);
            createAt.setCellValue(item.getCreatedAt());
            createAt.setCellStyle(dateCellStyle);

            Cell qaCardDate = row.createCell(5);
            qaCardDate.setCellValue(item.getQaCardDate());
            qaCardDate.setCellStyle(dateCellStyle);

            Cell lotDate = row.createCell(6);
            lotDate.setCellValue(item.getLotDate());
            lotDate.setCellStyle(dateCellStyle);

            row.createCell(7).setCellValue(item.getQaCard());
            row.createCell(8).setCellValue(item.getLotLine());
            row.createCell(9).setCellValue(item.getLotShift());
            row.createCell(10).setCellValue(item.getLotNo());
            row.createCell(11).setCellValue(item.getPartNo());
            row.createCell(12).setCellValue(item.getQty());
            row.createCell(13).setCellValue(item.getUsername());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream result = new ByteArrayInputStream(out.toByteArray());

        // Close the workbook and output stream
        workbook.close();
        Files.deleteIfExists(Paths.get(pathFile));
        return result;
    }

}
