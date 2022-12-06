package pidvn.modules.warehouse.iqc.services;

import pidvn.entities.one.IqcRequest;
import pidvn.modules.warehouse.iqc.models.*;

import java.util.List;

public interface IWhIqcService {
    List<InvoiceVo> getInvoices();
    List<IqcRequestDetailVo> getIqcRequestDetail(IqcRequestSearchVo searchVo);
    IqcRequest createIqcRequest(IqcRequestVo iqcRequest) throws Exception;
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
    List<String> getSlipNoByInvoice(String invoice);
}
