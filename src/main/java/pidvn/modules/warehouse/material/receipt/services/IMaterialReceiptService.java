package pidvn.modules.warehouse.material.receipt.services;

import pidvn.entities.one.Lots;
import pidvn.entities.one.PurWhHeaders;
import pidvn.modules.warehouse.material.receipt.models.*;

import java.util.List;
import java.util.Map;

public interface IMaterialReceiptService {

    Map saveMaterials(List<MaterialVo> materials) throws Exception;

    List<PurWhRecordsVo> getMaterials(PurWhRecordsSearchVo searchVo);

    List<LotVo> getLotsByLotNo(LotSearchVo searchVo);

    Map saveBigBox(List<MaterialVo> materials) throws Exception;

    List<InvoiceVo> getInvoices(InvoiceSearchVo searchVo);

    List<InvoiceDetailVo> getInvoiceDetail(InvoiceSearchVo searchVo);

    List<PurWhRecordsVo> findSlipByInvoice(String invoice);

    void deleteRecord(PurWhRecordsVo purWhRecordsVo);

    List<Lots> getDetailOuterLabel(List<String> outerLabels);

    PurWhHeaders createPurWhHeader(PurWhHeaderVo purWhHeaderVo);
}
