package pidvn.modules.warehouse.material.receipt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.warehouse.material.receipt.MaterialReceiptMapper;
import pidvn.modules.warehouse.material.receipt.models.*;
import pidvn.repositories.one.*;

import java.util.*;


@Service
public class MaterialReceiptService implements IMaterialReceiptService {


    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private MaterialReceiptMapper materialReceiptMapper;

    @Autowired
    private InoutLabelsRepo inoutLabelsRepo;

    @Autowired
    private PurWhHeadersRepo purWhHeaderRepo;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    /**
     * Lưu data vào bảng pur_wh_records
     *
     * @param materials
     * @return
     */
    @Override
    public Map saveMaterials(List<MaterialVo> materials) throws Exception {

        List<String> duplicateList = new ArrayList<>();
        int amountSaved = 0;

        this.validateModel(materials.get(0).getModel());

        for (MaterialVo material : materials) {

            // TODO: kiểm tra dữ liệu bị trùng
            // Nếu trùng thì ko lưu
            if (this.isDuplicateData(material)) {
                duplicateList.add(material.getQrCode());
                continue;
            }

            // TODO: Kiểm tra lots
            Lots lot = this.lotsRepo.findByLotNo(material.getLotNo());
            if (lot == null) {
                // Nếu chưa có lotNo thì tạo lotNo
                Lots obj = new Lots();
                obj.setLotNo(material.getLotNo().toUpperCase());
                obj.setModel(material.getModel().toUpperCase());
                obj.setDate(new Date());
                obj.setQty(material.getQty());
                obj.setFirstQty(material.getQty());
                obj.setUserCode(material.getWhUserCode());
                obj.setLotGroup(material.getLotGroup());
                obj.setInvoice(material.getInvoiceNo());
                obj.setVendorCode(material.getVendorCode());
                obj.setPo(material.getPoNo());
                obj.setPoSeq(material.getPoSeq());
                obj.setType("R");

                this.lotsRepo.save(obj);
            }
//            else {
//                // Nếu đã có: thì cộng dồn số lượng
//                Float qty = lot.getQty();
//                qty += material.getQty();
//                lot.setQty(qty);
//                this.lotsRepo.save(lot);
//            }

            // Lưu data vào bảng pur_wh_records
            PurWhRecords obj = new PurWhRecords();
            obj.setLotNo(material.getLotNo().toUpperCase());
            obj.setRecordType("IM");
            obj.setQty(material.getQty());
            obj.setModel(material.getModel().toUpperCase());
            obj.setDate(new Date());
            obj.setPihUserCode(material.getWhUserCode());
            obj.setWhUserCode(material.getWhUserCode());
            obj.setFlag("1");
            obj.setVendorCode(material.getVendorCode());
            obj.setInvoice(material.getInvoiceNo());
            obj.setPo(material.getPoNo());
            obj.setSlipNo(material.getSlipNo());

            if (material.getSerial() != "" ) {
                if (material.getSerial().chars().allMatch(Character::isDigit)) {
                    obj.setSerial(Integer.parseInt(material.getSerial()));
                }
            }

            obj.setSpool(material.getSpoolNo());

            this.purWhRecordsRepo.save(obj);

            amountSaved = amountSaved + 1;
        }

        Map response = new HashMap();
        response.put("duplicateList", duplicateList);
        response.put("amountSaved", amountSaved);


        /**
         * Trường hợp scan thêm hàng vào request
         */
        String slipNo = materials.get(0).getSlipNo();
        IqcRequest request = this.iqcRequestRepo.findByRequestNo(slipNo);
        // Nếu đã tồn tại request thì cập nhật trạng thái request thành 1 (st)
        if (request != null) {
            request.setStatus(1);
            this.iqcRequestRepo.save(request);
        }

        return response;
    }

    @Override
    public List<PurWhRecordsVo> getMaterials(PurWhRecordsSearchVo searchVo) {
        return this.materialReceiptMapper.getPurWhRecords(searchVo);
    }

    @Override
    public List<LotVo> getLotsByLotNo(LotSearchVo searchVo) {
        return this.materialReceiptMapper.getLotsByLotNo(searchVo.getLots());
    }

    /**
     * Lưu dữ liệu thùng to
     * @param materials
     * @return
     * @throws Exception
     */
    @Override
    public Map saveBigBox(List<MaterialVo> materials) throws Exception {
        List<String> duplicateList = new ArrayList<>();
        int amountSaved = 0;
        this.validateModel(materials.get(0).getModel());

        for (MaterialVo material : materials) {

            // TODO: kiểm tra dữ liệu bị trùng
            // Nếu trùng thì ko lưu
            if (this.isDuplicateData(material)) {
                duplicateList.add(material.getLotNo());
                continue;
            }

            // Lưu data vào bảng pur_wh_records
            PurWhRecords obj = new PurWhRecords();
            obj.setLotNo(material.getLotNo().toUpperCase());
            obj.setRecordType("IM");
            obj.setQty(material.getQty());
            obj.setModel(material.getModel());
            obj.setDate(new Date());
            obj.setPihUserCode(material.getWhUserCode());
            obj.setWhUserCode(material.getWhUserCode());
            obj.setFlag("1");
            obj.setVendorCode(material.getVendorCode());
            obj.setInvoice(material.getInvoiceNo());
            obj.setSlipNo(material.getSlipNo());
            obj.setPo(material.getPoNo());

            this.purWhRecordsRepo.save(obj);

            amountSaved = amountSaved + 1;

        }

        Map response = new HashMap();
        response.put("duplicateList", duplicateList);
        response.put("amountSaved", amountSaved);

        /**
         * Trường hợp scan thêm hàng vào request
         */
        String slipNo = materials.get(0).getSlipNo();
        IqcRequest request = this.iqcRequestRepo.findByRequestNo(slipNo);
        // Nếu đã tồn tại request thì cập nhật trạng thái request thành 1 (st)
        if (request != null) {
            request.setStatus(1);
            this.iqcRequestRepo.save(request);
        }

        return response;
    }

    @Override
    public List<InvoiceVo> getInvoices(InvoiceSearchVo searchVo) {
        return this.materialReceiptMapper.getInvoices(searchVo);
    }

    @Override
    public List<InvoiceDetailVo> getInvoiceDetail(InvoiceSearchVo searchVo) {
        return this.materialReceiptMapper.getInvoiceDetail(searchVo);
    }

    /**
     * Tìm Slip theo Invoice
     * @param invoice
     * @return
     */
    @Override
    public List<PurWhRecordsVo> findSlipByInvoice(String invoice) {
        List<PurWhRecordsVo> result = this.materialReceiptMapper.getPurWhRecordsByInvoice(invoice);
        return result;
    }

    @Override
    public void deleteRecord(PurWhRecordsVo purWhRecordsVo) {
        PurWhRecords obj = this.purWhRecordsRepo.getById(purWhRecordsVo.getId());
        this.purWhRecordsRepo.delete(obj);
    }

    @Override
    public List<Lots> getDetailOuterLabel(List<String> outerLabels) {

        List<InoutLabels> inoutLabels = this.inoutLabelsRepo.findByOuterLotNoIn(outerLabels);

        List<String> lots = new ArrayList<>();

        for (InoutLabels item: inoutLabels) {
            lots.addAll(Arrays.asList(item.getInnerLabels().split(";")));
        }

        return this.lotsRepo.findByLotNoIn(lots);
    }



    @Override
    public PurWhHeaders createPurWhHeader(PurWhHeaderVo purWhHeaderVo) {

        // Check đã có dữ liệu chưa
        PurWhHeaders data = this.purWhHeaderRepo.findByRegNo(purWhHeaderVo.getRegNo());

        if (data == null) {
            PurWhHeaders headerSave = new PurWhHeaders();
            headerSave.setRegNo(purWhHeaderVo.getRegNo());
            headerSave.setDate(new Date());
            headerSave.setActualDate(new Date());
            headerSave.setSerial(1);
            headerSave.setType(purWhHeaderVo.getType());
            headerSave.setUserId(purWhHeaderVo.getUserId());
            return this.purWhHeaderRepo.save(headerSave);
        }

        return data;
    }

    /**
     * Kiểm tra nếu trong CSDL ko có model thì phải thêm model
     *
     * @param modelName tên Model
     * @throws Exception
     */
    private void validateModel(String modelName) throws Exception {
        Model model = this.modelRepo.findByName(modelName);
        if (model == null) {
            throw new Exception("Cần thêm dữ liệu model: " + modelName);
        }
    }

    /**
     * Kiểm tra dữ liệu bị trùng lặp
     *
     * @return
     */
    private boolean isDuplicateData(MaterialVo materialVo) {
        PurWhRecordsSearchVo searchVo = new PurWhRecordsSearchVo();
        searchVo.setVendorCode(materialVo.getVendorCode());
        if (materialVo.getSerial() != "" && materialVo.getSerial() != null) {
            // Kiểm tra serial có parse ra kiểu number đc ko
            if (materialVo.getSerial().chars().allMatch(Character::isDigit)){
                searchVo.setSerial(Integer.parseInt(materialVo.getSerial()));
            }else {
                searchVo.setSerial(null);
            }
        }else {
            searchVo.setSerial(null);
        }
        searchVo.setLotNo(materialVo.getLotNo());
        List<PurWhRecordsVo> result = this.materialReceiptMapper.getPurWhRecords(searchVo);
        return result.size() > 0 ? true : false;
    }
}
