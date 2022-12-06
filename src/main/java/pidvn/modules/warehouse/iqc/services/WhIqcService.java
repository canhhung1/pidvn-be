package pidvn.modules.warehouse.iqc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.PurWhRecords;
import pidvn.mappers.one.warehouse.iqc.WhIqcMapper;
import pidvn.modules.warehouse.iqc.models.*;
import pidvn.repositories.one.IqcRequestRepo;
import pidvn.repositories.one.PurWhRecordsRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WhIqcService implements IWhIqcService {

    @Autowired
    private WhIqcMapper whIqcMapper;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Override
    public List<InvoiceVo> getInvoices() {
        return this.whIqcMapper.getInvoices();
    }

    @Override
    public List<IqcRequestDetailVo> getIqcRequestDetail(IqcRequestSearchVo searchVo) {
        return this.whIqcMapper.getIqcRequestDetail(searchVo);
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
}
