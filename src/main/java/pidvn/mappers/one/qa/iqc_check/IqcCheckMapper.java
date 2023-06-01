package pidvn.mappers.one.qa.iqc_check;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.qa.iqc_check.models.*;

import java.util.List;

@Mapper
public interface IqcCheckMapper {
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
    List<IqcDataVo> getIqcDataMaster(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataDetail(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataSortingMaster(String requestNo);
    List<IqcDataVo> getIqcDataSortingDetail(String requestNo);
}
