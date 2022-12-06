package pidvn.modules.is.device_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.DmDevice;
import pidvn.entities.one.DmHistory;
import pidvn.modules.is.device_management.models.DeviceSearchVo;
import pidvn.modules.is.device_management.models.HandOverInfoVo;
import pidvn.modules.is.device_management.models.HistorySearchVo;
import pidvn.modules.is.device_management.models.HistoryVo;
import pidvn.modules.is.device_management.services.DeviceManagementService;

import java.util.List;

@RestController
@RequestMapping("IS/DeviceManagement")
public class DeviceManagementController {

    @Autowired
    private DeviceManagementService deviceMngSvc;

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.deviceMngSvc.getUsers(), HttpStatus.OK);
    }

    @PostMapping("Devices")
    public ResponseEntity<?> getDevices(@RequestBody DeviceSearchVo searchVo) {
        return new ResponseEntity<>(this.deviceMngSvc.getDevices(searchVo), HttpStatus.OK);
    }

    @PostMapping("CreateDevice")
    public ResponseEntity<?> createDevice(@RequestBody DmDevice device) {
        return new ResponseEntity<>(this.deviceMngSvc.createDevice(device), HttpStatus.OK);
    }

    @GetMapping("DeviceType")
    public ResponseEntity<?> getDeviceType() {
        return new ResponseEntity<>(this.deviceMngSvc.getDeviceType(), HttpStatus.OK);
    }

    @GetMapping("DeviceStatus")
    public ResponseEntity<?> getDeviceStatus() {
        return new ResponseEntity<>(this.deviceMngSvc.getDeviceStatus(), HttpStatus.OK);
    }

    @PostMapping("HandOverDevice")
    public ResponseEntity<?> handOverDevice(@RequestBody HandOverInfoVo infoVo) {
        DmHistory result = this.deviceMngSvc.handOverDevice(infoVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("ReceiveDevice")
    public ResponseEntity<?> receiveDevice(@RequestBody HandOverInfoVo infoVo) {
        DmHistory result = this.deviceMngSvc.receiveDevice(infoVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("Histories")
    public ResponseEntity<?> getHistories(@RequestBody HistorySearchVo searchVo) {
        List<HistoryVo> result = this.deviceMngSvc.getHistories(searchVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
