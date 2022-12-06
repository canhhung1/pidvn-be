package pidvn.mappers.one.is.device_management;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.is.device_management.models.DeviceSearchVo;
import pidvn.modules.is.device_management.models.DeviceVo;
import pidvn.modules.is.device_management.models.HistorySearchVo;
import pidvn.modules.is.device_management.models.HistoryVo;

import java.util.List;

@Mapper
public interface DeviceManagementMapper {
    List<DeviceVo> getDevices(DeviceSearchVo searchVo);
    List<HistoryVo> getHistories(HistorySearchVo searchVo);
}
