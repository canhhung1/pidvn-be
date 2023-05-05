package pidvn.mappers.one.packing.oqc_request;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.packing.oqc_request.models.DataSummaryVo;
import pidvn.modules.packing.oqc_request.models.SearchVo;

import java.util.List;

@Mapper
public interface PackingOqcRequestMapper {
    List<DataSummaryVo> summaryData(SearchVo searchVo);
}
