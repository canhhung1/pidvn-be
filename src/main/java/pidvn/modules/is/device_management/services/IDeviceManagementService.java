package pidvn.modules.is.device_management.services;

import pidvn.entities.one.*;
import pidvn.modules.is.device_management.models.*;

import java.util.List;

public interface IDeviceManagementService {
    List<DeviceVo> getDevices(DeviceSearchVo searchVo);
    DmDevice createDevice(DmDevice device);
    List<DmDeviceType> getDeviceType();
    List<DmDeviceStatus> getDeviceStatus();
    List<Users> getUsers();
    DmHistory handOverDevice(HandOverInfoVo infoVo);
    DmHistory receiveDevice(HandOverInfoVo infoVo);
    List<HistoryVo> getHistories(HistorySearchVo searchVo);
}
