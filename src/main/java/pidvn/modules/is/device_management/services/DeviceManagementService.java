package pidvn.modules.is.device_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.is.device_management.DeviceManagementMapper;
import pidvn.modules.is.device_management.models.*;
import pidvn.repositories.one.*;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceManagementService implements IDeviceManagementService {

    @Autowired
    private DeviceManagementMapper deviceManagementMapper;

    @Autowired
    private DmDeviceRepo deviceRepo;

    @Autowired
    private DmDeviceTypeRepo deviceTypeRepo;

    @Autowired
    private DmDeviceStatusRepo deviceStatusRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private DmHistoryRepo historyRepo;

    @Override
    public List<DeviceVo> getDevices(DeviceSearchVo searchVo) {
        return this.deviceManagementMapper.getDevices(searchVo);
    }

    @Override
    public DmDevice createDevice(DmDevice device) {
        device.setStatus(1);
        return this.deviceRepo.save(device);
    }

    @Override
    public List<DmDeviceType> getDeviceType() {
        return this.deviceTypeRepo.findAll();
    }

    @Override
    public List<DmDeviceStatus> getDeviceStatus() {
        return this.deviceStatusRepo.findAll();
    }

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAll();
    }

    @Override
    public DmHistory handOverDevice(HandOverInfoVo infoVo) {

        DmDevice device = this.deviceRepo.findById(infoVo.getId()).get();
        device.setStatus(0);
        this.deviceRepo.save(device);

        DmHistory obj = new DmHistory();
        obj.setActionType(1);
        obj.setDeviceId(infoVo.getId());
        obj.setEmployee(infoVo.getEmployee());
        obj.setSignature(infoVo.getSignature());
        obj.setNote(infoVo.getNote());

        return this.historyRepo.save(obj);
    }

    @Override
    public DmHistory receiveDevice(HandOverInfoVo infoVo) {
        DmDevice device = this.deviceRepo.findById(infoVo.getId()).get();
        device.setStatus(1);
        this.deviceRepo.save(device);

        DmHistory obj = new DmHistory();
        obj.setActionType(2);
        obj.setDeviceId(infoVo.getId());
        obj.setEmployee(infoVo.getEmployee());
        obj.setSignature(infoVo.getSignature());
        obj.setNote(infoVo.getNote());

        return this.historyRepo.save(obj);
    }

    @Override
    public List<HistoryVo> getHistories(HistorySearchVo searchVo) {
        return this.deviceManagementMapper.getHistories(searchVo);
    }
}
