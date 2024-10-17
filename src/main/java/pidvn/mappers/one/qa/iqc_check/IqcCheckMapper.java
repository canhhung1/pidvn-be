package pidvn.mappers.one.qa.iqc_check;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.IqcDataMaster;
import pidvn.entities.one.IqcRequestSortingDetail;
import pidvn.modules.qa.iqc_check.models.*;

import java.util.List;

@Mapper
public interface IqcCheckMapper {
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
    List<IqcDataVo> getIqcDataMaster(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataDetail(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataSortingMaster(String requestNo);
    List<IqcDataVo> getIqcDataSortingDetail(String requestNo, String lotGroup);
    List<IqcRequestSortingDetail> getIqcRequestSortingDetails(String requestNo, String lotGroup);
    List<IqcDataMaster> getMucDoKiemSoat(String model);
}
