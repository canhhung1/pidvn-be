package pidvn.mappers.one.warehouse.material.receipt;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.InoutLabels;
import pidvn.modules.warehouse.material.receipt.models.*;

import java.util.List;

@Mapper
public interface MaterialReceiptMapper {

    List<PurWhRecordsVo> getPurWhRecords(PurWhRecordsSearchVo searchVo);
    List<LotVo> getLotsByLotNo(List<String> lots);
    List<InvoiceVo> getInvoices(InvoiceSearchVo searchVo);
    List<InvoiceDetailVo> getInvoiceDetail(InvoiceSearchVo searchVo);

    List<PurWhRecordsVo> getPurWhRecordsByInvoice(String invoice);

    // Dữ liệu thêm vào request đã tồn tại
    List<IqcResultDto> dataAddToIqcRequest(String slipNo, Integer userId, List<String> lotNos);
}
