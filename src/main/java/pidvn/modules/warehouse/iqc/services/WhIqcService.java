package pidvn.modules.warehouse.iqc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.IqcRequestSortingDetail;
import pidvn.entities.one.PurWhRecords;
import pidvn.mappers.one.qa.iqc_check.IqcCheckMapper;
import pidvn.mappers.one.warehouse.iqc.WhIqcMapper;
import pidvn.modules.qa.iqc_check.models.IqcDataVo;
import pidvn.modules.warehouse.iqc.models.*;
import pidvn.repositories.one.IqcRequestRepo;
import pidvn.repositories.one.IqcRequestSortingDetailRepo;
import pidvn.repositories.one.PurWhRecordsRepo;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WhIqcService implements IWhIqcService {

    @Autowired
    private WhIqcMapper whIqcMapper;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    @Autowired
    private IqcRequestSortingDetailRepo iqcRequestSortingDetailRepo;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private IqcCheckMapper iqcCheckMapper;

    @Override
    public List<InvoiceVo> getInvoices() {
        return this.whIqcMapper.getInvoices();
    }

    @Override
    public List<IqcRequestDetailVo> getIqcRequestDetail(IqcRequestSearchVo searchVo) {

        List<IqcRequestDetailVo> result = this.whIqcMapper.getIqcRequestDetail(searchVo);

        return result;
    }

    @Override
    public IqcRequest createIqcRequest(IqcRequestVo iqcRequest) throws Exception {

        // Kiểm tra số requestNo không được trùng
        IqcRequest request = this.iqcRequestRepo.findByRequestNo(iqcRequest.getRequestNo());
        if (request != null) {
            throw new Exception("Request No đã tồn tại !");
        }

        IqcRequest obj = new IqcRequest();
        obj.setInvoice(iqcRequest.getInvoice());
        obj.setRequestNo(iqcRequest.getRequestNo());
        obj.setSupplier(iqcRequest.getSupplier().toUpperCase());
        obj.setRequestedBy(iqcRequest.getRequestedBy());
        obj.setStatus(1);
        obj.setRemark(iqcRequest.getRemark());
        return this.iqcRequestRepo.save(obj);
    }

    @Override
    public List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo) {
        return this.whIqcMapper.getIqcRequests(searchVo);
    }

    @Override
    public List<String> getSlipNoByInvoice(String invoice) {
        List<PurWhRecords> datas = this.purWhRecordsRepo.findByInvoice(invoice);
        Map<String, String> map = new HashMap();
        for (PurWhRecords pur : datas) {
            map.put(pur.getSlipNo(), pur.getSlipNo());
        }

        ArrayList<String> result = new ArrayList<>(map.values());


        return result;
    }

    @Override
    public IqcRequest createIqcRequestSorting(List<String> lotNos, String requestType) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        /**
         * 1. Tạo record trong bảng iqc_request type = 'sorting' or 'over6month'
         * 2. Lưu danh sách lotNo vào bảng iqc_request_sorting_detail
         */

        int totalRequest = this.iqcRequestRepo.getTotalRequestSortingInDay();
        int sequenceNo = totalRequest+=1;
        String strDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String reqNo = (requestType == "sorting" ? "RST-" : "ROD-") + strDate + "-" + sequenceNo;

        IqcRequest iqcRequest = new IqcRequest();
        iqcRequest.setRequestNo(reqNo);
        iqcRequest.setRequestedBy(auth.getName());
        iqcRequest.setStatus(1);
        iqcRequest.setType(requestType);

        IqcRequest req = this.iqcRequestRepo.save(iqcRequest);

        List<IqcRequestSortingDetail> listLot = new ArrayList<>();
        for (String lot: lotNos) {
            IqcRequestSortingDetail obj = new IqcRequestSortingDetail();
            obj.setRequestNo(reqNo);
            obj.setLotNo(lot);
            listLot.add(obj);
        }

        this.iqcRequestSortingDetailRepo.saveAll(listLot);

        return req;
    }

    @Override
    public Map getIqcDataSortingInfo(String requestNo) {

        Map result = new HashMap();

        List<IqcDataVo> masters = this.iqcCheckMapper.getIqcDataSortingMaster(requestNo);

        //List<IqcDataVo> details = this.iqcCheckMapper.getIqcDataSortingDetail(requestNo);

        result.put("masters", masters);
        //result.put("details", details);

        return result;
    }
}
