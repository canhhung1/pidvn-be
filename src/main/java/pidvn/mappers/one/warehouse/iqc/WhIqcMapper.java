package pidvn.mappers.one.warehouse.iqc;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.warehouse.iqc.models.*;

import java.util.List;

@Mapper
public interface WhIqcMapper {
    List<InvoiceVo> getInvoices();
    List<IqcRequestDetailVo> getIqcRequestDetail(IqcRequestSearchVo searchVo);
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
}
