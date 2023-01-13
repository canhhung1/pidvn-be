package pidvn.modules.packing.oqc_request.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.OqcRequest;
import pidvn.modules.packing.oqc_request.models.OqcRequestVo;
import pidvn.repositories.one.OqcRequestRepo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackingOqcRequestSvc implements IPackingOqcRequest {

    @Autowired
    private OqcRequestRepo oqcRequestRepo;

    @Override
    public Map createOqcRequest(OqcRequestVo oqcRequestVo) {

        // TODO

        /**
         * B1: Kiểm tra phiếu đã được tạo chưa
         */
        List<OqcRequest> reqNos = this.oqcRequestRepo.findByQaCard(oqcRequestVo.getQaCard());

        // Trường hợp đã tồn tại thì không cho tạo nữa
        if (reqNos.size() > 0) {
            Map result = new HashMap();
            result.put("status","ERROR");
            result.put("messages","Đã có request tạo cho QA card : " + oqcRequestVo.getQaCard());
            return result;
        }

        /**
         * B2: Tạo mã OQC request (req_no)
         */
        int totalRequest = this.oqcRequestRepo.getTotalRequestInDay();
        int sequenceNo = totalRequest+=1;
        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        String reqNo = "ROQC-" + strDate + "-" + sequenceNo;

        /**
         * B3: Insert vào bảng oqc_request
         */
        OqcRequest oqcReq = new OqcRequest();
        oqcReq.setReqNo(reqNo);
        oqcReq.setQaCard(oqcRequestVo.getQaCard());
        oqcReq.setOqcRequestStatus(1);
        oqcReq.setPriority(oqcRequestVo.getPriority());
        oqcReq.setCreatedBy(oqcRequestVo.getCreatedBy());

        OqcRequest data = this.oqcRequestRepo.save(oqcReq);

        Map result = new HashMap();
        result.put("status","OK");
        result.put("messages","Tạo request thành công");
        result.put("data", data);

        return result;
    }
}
