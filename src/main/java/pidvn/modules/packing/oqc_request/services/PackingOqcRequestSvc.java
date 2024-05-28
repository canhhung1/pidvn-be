package pidvn.modules.packing.oqc_request.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.OqcRequest;
import pidvn.mappers.one.packing.oqc_request.PackingOqcRequestMapper;
import pidvn.modules.packing.oqc_request.models.DataSummaryVo;
import pidvn.modules.packing.oqc_request.models.OqcRequestVo;
import pidvn.modules.packing.oqc_request.models.SearchVo;
import pidvn.repositories.one.OqcRequestRepo;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PackingOqcRequestSvc implements IPackingOqcRequest {

    @Autowired
    private OqcRequestRepo oqcRequestRepo;

    @Autowired
    private PackingOqcRequestMapper packingOqcRequestMapper;

    @Override
    public Map createOqcRequest(OqcRequestVo oqcRequestVo) {

        // TODO

        /**
         * B1: Kiểm tra phiếu đã được tạo chưa
         */
        List<OqcRequest> reqNos = this.oqcRequestRepo.findByQaCard(oqcRequestVo.getQaCard());

        /**
         * Trường hợp không phải là hàng sorting,
         * nếu đã tồn tại request thì không cho tạo mới
         */
        if (reqNos.size() > 0 && !oqcRequestVo.isSorting()) {
            Map result = new HashMap();
            result.put("status","ERROR");
            result.put("messages","Đã có request tạo cho QA card : " + oqcRequestVo.getQaCard());
            return result;
        }

        /**
         * B2: Tạo mã OQC request (req_no)
         */
        int totalRequest = this.oqcRequestRepo.getTotalRequestInMonth();
        int sequenceNo = totalRequest+=1;
        String strDate = new SimpleDateFormat("yy-MM").format(Calendar.getInstance().getTime());
        String reqNo = "ROQC-" + strDate + "-" + sequenceNo;

        /**
         * B3. Tạo request cho hàng sorting
         */
        if (oqcRequestVo.isSorting()) {

            /**
             * 1. Update lại request cũ thành NG, remark lại do hàng sorting
             */
            List<OqcRequest> oqcReqList = this.oqcRequestRepo.findByQaCard(oqcRequestVo.getQaCard());
            if (oqcReqList.size() > 0) {
                OqcRequest oldOqcReq = oqcReqList.get(oqcReqList.size()-1);
                oldOqcReq.setJudgment("NG");
                oldOqcReq.setRemark("System: NG do hàng sorting");
                oldOqcReq.setOqcDate(new Date());
                oldOqcReq.setOqcRequestStatus(3);
                this.oqcRequestRepo.save(oldOqcReq);
            }

            /**
             * 2. Tạo request mới
             */
            OqcRequest oqcReq = new OqcRequest();
            oqcReq.setReqNo(reqNo);
            oqcReq.setQaCard(oqcRequestVo.getQaCard());
            oqcReq.setOqcRequestStatus(1);
            oqcReq.setPriority(oqcRequestVo.getPriority());
            oqcReq.setCreatedBy(oqcRequestVo.getCreatedBy());
            oqcReq.setSortingQty(oqcRequestVo.getSortingQty());
            oqcReq.setRemark(oqcRequestVo.getRemark());
            oqcReq.setDeliveryDate(oqcRequestVo.getDeliveryDate());
            oqcReq.setFlag(1);
            OqcRequest data = this.oqcRequestRepo.save(oqcReq);

            Map result = new HashMap();
            result.put("status","OK");
            result.put("messages","Tạo request thành công");
            result.put("data", data);

            return result;
        }

        /**
         * B3: Tạo request cho hàng thường
         */
        OqcRequest oqcReq = new OqcRequest();
        oqcReq.setReqNo(reqNo);
        oqcReq.setQaCard(oqcRequestVo.getQaCard());
        oqcReq.setOqcRequestStatus(1);
        oqcReq.setPriority(oqcRequestVo.getPriority());
        oqcReq.setCreatedBy(oqcRequestVo.getCreatedBy());
        oqcReq.setSortingQty(null);
        oqcReq.setRemark(oqcRequestVo.getRemark());
        oqcReq.setDeliveryDate(oqcRequestVo.getDeliveryDate());
        oqcReq.setFlag(1);

        OqcRequest data = this.oqcRequestRepo.save(oqcReq);

        Map result = new HashMap();
        result.put("status","OK");
        result.put("messages","Tạo request thành công");
        result.put("data", data);

        return result;
    }

    @Override
    public OqcRequest updateOqcRequest(OqcRequestVo oqcRequestVo) {
        OqcRequest oqcRequestUpdate = this.oqcRequestRepo.findById(oqcRequestVo.getId()).get();
        oqcRequestUpdate.setSortingQty(oqcRequestVo.getSortingQty());
        oqcRequestUpdate.setDeliveryDate(oqcRequestVo.getDeliveryDate());
        oqcRequestUpdate.setPriority(oqcRequestVo.getPriority());
        oqcRequestUpdate.setRemark(oqcRequestVo.getRemark());
        oqcRequestUpdate.setOqcRequestStatus(oqcRequestVo.getRequestStatusId());
        return this.oqcRequestRepo.save(oqcRequestUpdate);
    }

    private String createOqcRequestNo() {

        // List<OqcRequest> requests = this.oqcRequestRepo.getAllByCurrentMonth();

        



        return "";
    }

    @Override
    public List<DataSummaryVo> summaryData(SearchVo searchVo) {
        return this.packingOqcRequestMapper.summaryData(searchVo);
    }
}
