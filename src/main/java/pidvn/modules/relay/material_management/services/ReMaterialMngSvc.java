package pidvn.modules.relay.material_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.*;
import pidvn.entities.one.Process;
import pidvn.mappers.one.relay.material_management.ReMaterialMngMapper;
import pidvn.modules.relay.material_management.models.PurWhRecordSearchVo;
import pidvn.modules.relay.material_management.models.PurWhRecordVo;
import pidvn.modules.relay.material_management.models.SearchVo;
import pidvn.repositories.one.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReMaterialMngSvc implements IReMaterialMngSvc {

    @Autowired
    private ReMaterialMngMapper reMatMngMapper;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private TmpAdrTnmu010Repo tmpAdrTnmu010Repo;

    @Autowired
    private LineRepo lineRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private InspectQaCardRepo inspectQaCardRepo;

    @Override
    public List<Line> getLines(Integer productId) {
        return this.lineRepo.findByProductIdOrderByName(productId);
    }

    @Override
    public List<Process> getProcesses(Integer productId) {
        return this.processRepo.findByProductId(productId);
    }

    @Override
    public List<TmpAdrTnmu010> getPartsByModel(String model) {
        return this.tmpAdrTnmu010Repo.findByPnpa(model);
    }

    @Override
    public List<PurWhRecordVo> getPurWhRecords(PurWhRecordSearchVo searchVo) {
        return this.reMatMngMapper.getPurWhRecords(searchVo);
    }

    @Override
    public Map scanMaterial(PurWhRecordVo purWhRecordVo) {

        Lots lot = this.lotsRepo.findByLotNo(purWhRecordVo.getLotNo());

        Map result = new HashMap();
        String message = "";

        String action = purWhRecordVo.getRecordType();

        /**
         * Kho trung chuyển (RE-WH) nhận nguyên vật liệu
         */
        if ("RNP".equals(action)) {

            /**
             * Trường hợp: RE-WH nhận NVL từ PUR-WH
             */
            String [] recordTypes = new String[] {"RNP","RDC","CDL","LTC","CTR","RTP"};
            List<PurWhRecords> records = this.purWhRecordsRepo.findByLotNoAndRecordTypeInOrderByIdDesc(purWhRecordVo.getLotNo(), Arrays.asList(recordTypes));

            if (records.size() > 0) {

                PurWhRecords recordLatest = records.get(0);

                if ("RNP".equals(recordLatest.getRecordType())) {
                    message = "Lot này đã được SCAN nhập kho trung chuyển (RE-WH)";
                    result.put("status", "ERROR");
                    result.put("message", message);
                    return result;
                }else if("RDC".equals(recordLatest.getRecordType())) {
                    message = "Lot này đã được nhập vào xe NVL (LINE-WH)";
                    result.put("status", "ERROR");
                    result.put("message", message);
                    return result;
                }else if ("CDL".equals(recordLatest.getRecordType())) {
                    message = "Lot này đã được nhập Line (LINE)";
                    result.put("status", "ERROR");
                    result.put("message", message);
                    return result;
                } else if ("LTC".equals(recordLatest.getRecordType())) {
                    message = "Lot này đã được nhập vào xe NVL (LINE-WH)";
                    result.put("status", "ERROR");
                    result.put("message", message);
                    return result;
                } else if ("RTP".equals(recordLatest.getRecordType())) {
                    message = "Lot này đã được trả về kho NVL (PUR-WH)";
                    result.put("status", "ERROR");
                    result.put("message", message);
                    return result;
                }
            }
            message = "Có thể nhập NVL vào kho trung chuyển (RE-WH)";
            result.put("status", "OK");
            result.put("message", message);
            result.put("data", purWhRecordVo);
            return result;

        }

        /**
         * Chuyển NVL vào xe NVL (LINE-WH)
         */
        if ("RDC".equals(action)) {
            /**
             * Trường hợp: RE-WH chuyển NVL vào xe NVL (LINE-WH)
             */
            String [] recordTypes;
            List<PurWhRecords> records;

            recordTypes = new String[] {"RNP","RTP"};
            records = this.purWhRecordsRepo.findByLotNoAndRecordTypeInOrderByIdDesc(purWhRecordVo.getLotNo(), Arrays.asList(recordTypes));

            /**
             * TH1: NVL chưa được nhập vào kho trung chuyển (RE-WH)
             */
            if (records.size() <= 0) {
                message = "Lot này chưa được SCAN nhập từ Warehouse vào kho trung chuyển (RE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }

            /**
             * TH2: NVL đã được nhập vào kho trung chuyển (RE-WH)
             * 1. Kiểm tra NVL đã được nhập vào LINE trước đó chưa, nếu rồi thì đang thuộc QA card nào
             */

            recordTypes = new String[] {"RDC","CDL","LTC","CTR"};
            records = this.purWhRecordsRepo.findByLotNoAndRecordTypeInOrderByIdDesc(purWhRecordVo.getLotNo(), Arrays.asList(recordTypes));
            Float actualQty;

            if (records.size() <= 0) {
                // Không có record nào => chưa được SCAN nhập vào xe NVL
                message = "Có thể nhập NVL vào xe NVL (LINE-WH)";
                actualQty = purWhRecordVo.getQty() - this.getActualQtyInLine(records);
                purWhRecordVo.setOriginQty(purWhRecordVo.getQty());
                purWhRecordVo.setActualQty(actualQty);
                purWhRecordVo.setQty(actualQty);
                purWhRecordVo.setLineQty(this.getActualQtyInLine(records));
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }

            PurWhRecords recordLatest = records.get(0);

            if ("RDC".equals(recordLatest.getRecordType()) ||
                "CDL".equals(recordLatest.getRecordType()) ||
                "LTC".equals(recordLatest.getRecordType())) {
                message = "Lot: " + purWhRecordVo.getLotNo() +" đã được nhập vào QA card: "
                        + recordLatest.getQaCard() + " bởi nhân viên: " + recordLatest.getSender() + " lúc: " + recordLatest.getCreatedAt();
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("CTR".equals(recordLatest.getRecordType())) {
                message = "Có thể nhập NVL vào xe NVL (LINE-WH)";
                actualQty = purWhRecordVo.getQty() - this.getActualQtyInLine(records);
                purWhRecordVo.setOriginQty(purWhRecordVo.getQty());
                purWhRecordVo.setActualQty(actualQty);
                purWhRecordVo.setQty(actualQty);
                purWhRecordVo.setLineQty(this.getActualQtyInLine(records));
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }
        }

        /**
         * Đổ nguyên vật liệu vào LINE
         */
        if ("CDL".equals(action)) {
            String [] recordTypes;
            List<PurWhRecords> records;
            Float actualQty;

            recordTypes = new String[] {"RNP","RDC","CDL","LTC","CTR","RTP"};
            records = this.purWhRecordsRepo.findByLotNoAndRecordTypeInOrderByIdDesc(purWhRecordVo.getLotNo(), Arrays.asList(recordTypes));

            if (records.size() <= 0) {
                message = "Lot: " + purWhRecordVo.getLotNo() + " chưa được nhập vào kho trung chuyển (RE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }

            PurWhRecords recordLatest = records.get(0);

            if ("RNP".equals(recordLatest.getRecordType())) {
                message = "Lot: " + purWhRecordVo.getLotNo() + " chưa được nhập từ trung chuyển (RE-WH) vào xe chở NVL (LINE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("RDC".equals(recordLatest.getRecordType())) {
                // TH này NVL đang ở (LINE-WH)
                actualQty = purWhRecordVo.getQty() - this.getActualQtyInLine(records);
                message = "Có thể nhập NVL vào LINE";
                purWhRecordVo.setOriginQty(purWhRecordVo.getQty());
                purWhRecordVo.setActualQty(actualQty);
                purWhRecordVo.setQty(actualQty);
                purWhRecordVo.setLineQty(this.getActualQtyInLine(records));
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("CDL".equals(recordLatest.getRecordType())) {
                /**
                 * TH này NVL đã được đổ vào LINE
                 * 1. Nếu số lượng còn lại thực tế bằng 0 => NVL đã được đổ hết vào LINE
                 * 2. Nếu số lượng còn lại thực tế lớn hơn 0 => NVL mới chỉ được đổ vào một phần => có thể đổ tếp lượng tồn vào LINE
                 */
                actualQty = purWhRecordVo.getQty() - this.getActualQtyInLine(records);
                // TH1: tồn = 0
                if (actualQty <= 0) {
                    message = "Lot: " + purWhRecordVo.getLotNo() + " đã được nhập vào QA card : "
                            + recordLatest.getQaCard() + " lúc: " + recordLatest.getCreatedAt() + " bởi ID: " + recordLatest.getSender();
                    result.put("status", "ERROR");
                    result.put("message", message);
                    result.put("data", purWhRecordVo);
                    return result;
                }
                // TH2: tồn != 0
                message = "Có thể nhập NVL vào LINE";
                purWhRecordVo.setOriginQty(purWhRecordVo.getQty());
                purWhRecordVo.setActualQty(actualQty);
                purWhRecordVo.setQty(actualQty);
                purWhRecordVo.setLineQty(this.getActualQtyInLine(records));
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("LTC".equals(recordLatest.getRecordType())) {
                // TH này NVL từ LINE được trả về (LINE-WH) => nên có thể nhập được lại vào LINE
                message = "Có thể nhập NVL vào LINE";
                actualQty = purWhRecordVo.getQty() - this.getActualQtyInLine(records);
                purWhRecordVo.setOriginQty(purWhRecordVo.getQty());
                purWhRecordVo.setActualQty(actualQty);
                purWhRecordVo.setQty(actualQty);
                purWhRecordVo.setLineQty(this.getActualQtyInLine(records));
                result.put("status", "OK");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("CTR".equals(recordLatest.getRecordType())) {
                // TH này NVL từ LINE-WH trả ra kho trung chuyển (RE-WH)
                message = "Lot: " + purWhRecordVo.getLotNo() + " đã được trả ra kho trung chuyển (RE-WH) lúc: " + recordLatest.getCreatedAt() + " bởi ID: " + recordLatest.getSender();
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }else if ("RTP".equals(recordLatest.getRecordType())) {
                // TH này NVL đã được trả về kho PUR-WH
                message = "Lot: " + purWhRecordVo.getLotNo() + " chưa được nhập vào kho trung chuyển (RE-WH)";
                result.put("status", "ERROR");
                result.put("message", message);
                result.put("data", purWhRecordVo);
                return result;
            }


        }

        /**
         * LINE trả NVL về xe NVL (LINE-WH)
         */
        if ("LTC".equals(action)) {

        }

        /**
         * Xe NVL trả về RE-WH
         */



        return result;
    }

    /**
     * Qty đã cho vào LINE
     * @param records
     * @return
     */
    private float getActualQtyInLine(List<PurWhRecords> records) {
        float actualQty = 0;
        for (PurWhRecords item: records) {
            if ("CDL".equals(item.getRecordType())) {
                actualQty+=item.getQty();
            }
            if ("LTC".equals(item.getRecordType())) {
                actualQty-=item.getQty();
            }
        }
        return actualQty;
    }

    @Override
    public List<PurWhRecords> savePurWhRecords(List<PurWhRecords> purWhRecords) {
        return this.purWhRecordsRepo.saveAll(purWhRecords);
    }

    /**
     * Hiển thị danh sách các Lot có trong phiếu xuất kho
     * Và hiển thị các Lot được nhập kho trung chuyển (RE-WH)
     * @param slipNo
     * @return
     */
    @Override
    public Map getLotsBySlipNo(String slipNo) {

        List<PurWhRecordVo> models = this.reMatMngMapper.getLotsBySlipNo(slipNo, "model");
        List<PurWhRecordVo> lotGroups = this.reMatMngMapper.getLotsBySlipNo(slipNo, "lot_group");
        List<PurWhRecordVo> lots = this.reMatMngMapper.getLotsBySlipNo(slipNo, null);

        Map result = new HashMap();

        result.put("models", models);
        result.put("lotGroups", lotGroups);
        result.put("lots", lots);

        return result;
    }

    @Override
    public Integer deletePurWhRecord(Integer id) {
        this.purWhRecordsRepo.deleteById(id);
        return id;
    }

    @Transactional("transactionManagerOne")
    @Override
    public Map changeQaCard(String oldQaCard, String newQaCard) throws ParseException {

        PurWhRecordSearchVo searchVo = new PurWhRecordSearchVo();
        searchVo.setQaCard(oldQaCard);

        List<PurWhRecordVo> lots = this.reMatMngMapper.getActualQtyLineWh(searchVo);

        List<PurWhRecords> listUpdate = new ArrayList<>();
        List<PurWhRecords> listCreate = new ArrayList<>();

        for (PurWhRecordVo item: lots) {

            /**
             * TH này gói NVL chưa được đổ hết vào LINE (vẫn còn nguyên)
             */
            if (item.getActualQty() == 0){
                continue;
            }

            //
            /**
             * TH này gói NVL chưa được đổ vào LINE (vẫn còn nguyên)
             * Nên sẽ chi cần update QA Card của lot này sang QA Card mới
             */
            if (item.getOriginQty().floatValue() == item.getActualQty().floatValue()) {
                PurWhRecords lot = this.purWhRecordsRepo.findById(item.getId()).get();

                String [] qaCardInfo = newQaCard.split("\\*");
                lot.setQaCard(newQaCard);
                lot.setParent(qaCardInfo[0]);
                lot.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(qaCardInfo[2]));
                lot.setLine(qaCardInfo[1]);
                lot.setShift(qaCardInfo[3]);
                listUpdate.add(lot);

            }

            /**
             * TH này gói NVL chưa được đổ vào LINE một phần (chưa đổ hết vào LINE)
             */
            if (item.getOriginQty().floatValue() > item.getActualQty().floatValue()) {

                // Số lượng đã được đổ vào LINE sẽ update lại số lượng
                PurWhRecords lot = this.purWhRecordsRepo.findById(item.getId()).get();
                lot.setQty(item.getLineQty());
                listUpdate.add(lot);

                String [] qaCardInfo = newQaCard.split("\\*");

                // Số lượng tồn sẽ tạo record mới và QA Card mới
                PurWhRecords lotCreate = new PurWhRecords();
                lotCreate.setLotNo(lot.getLotNo());
                lotCreate.setRecordType(lot.getRecordType());
                lotCreate.setQty(item.getActualQty());
                lotCreate.setModel(lot.getModel());
                lotCreate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(qaCardInfo[2]));
                lotCreate.setParent(qaCardInfo[0]);
                lotCreate.setQaCard(newQaCard);
                lotCreate.setLine(qaCardInfo[1]);
                lotCreate.setShift(qaCardInfo[3]);
                lotCreate.setWhUserCode(lot.getWhUserCode());
                lotCreate.setSender(lot.getSender());
                lotCreate.setReceiver(lot.getReceiver());
                lotCreate.setFlag(lot.getFlag());

                listCreate.add(lotCreate);
            }

        }

        List<PurWhRecords> resultUpdate = this.purWhRecordsRepo.saveAll(listUpdate);
        List<PurWhRecords> resultCreate = this.purWhRecordsRepo.saveAll(listCreate);

        Map result = new HashMap();
        result.put("resultUpdate", resultUpdate);
        result.put("resultCreate", resultCreate);

        return result;
    }

    @Override
    public InspectQaCard saveInspectQACard(InspectQaCard inspectQaCard) {
        return this.inspectQaCardRepo.save(inspectQaCard);
    }

    @Override
    public List<InspectQaCard> getInspectQaCards(String inspectQaCard) {
        return this.inspectQaCardRepo.findByInspectQaCard(inspectQaCard);
    }

    @Override
    public Integer deleteInspectQaCard(Integer id) {
        this.inspectQaCardRepo.deleteById(id);
        return id;
    }

    @Override
    public List<PurWhRecordVo> materialTraceability(SearchVo searchVo) {
        return this.reMatMngMapper.materialTraceability(searchVo);
    }


}
