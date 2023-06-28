package pidvn.modules.qa.qa_equipment_mng.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.QaDevice;
import pidvn.entities.one.QaDeviceCalibrationLabel;
import pidvn.modules.qa.qa_equipment_mng.models.LabelVo;
import pidvn.modules.qa.qa_equipment_mng.models.QaDocDeviceVo;
import pidvn.modules.qa.qa_equipment_mng.services.QaEquipmentMngSvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Qa/EquipmentMng")
public class QaEquipmentMngCtrl {

    @Autowired
    private QaEquipmentMngSvc qaEquipmentMngSvc;

    @PostMapping("CreateLabel")
    public ResponseEntity<?> createLabel(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.createLabel(file), HttpStatus.OK);
    }

    /**
     * Download template tạo tem cho thiết bị
     * @return
     */
    @PostMapping("TemplateCreateLabel")
    public ResponseEntity<byte[]> downloadTemplateCreateLabel() {

        String fileUrl = "P:\\IS\\CanhHung\\FDCS\\QA\\QC\\Template_EquipmentLabelData.xlsx";

        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);
    }

    @PostMapping("Upload")
    public ResponseEntity<?> uploadDocument(
            @RequestBody MultipartFile file, @RequestParam String controlNo, @RequestParam Integer deviceId, @RequestParam Integer fileType, @RequestParam String createdBy
    ) throws IOException {

        Map result = this.qaEquipmentMngSvc.uploadDocument(file, controlNo, deviceId, fileType, createdBy);

        if (result.get("status").equals("ERROR")) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("QaDocDevices/{deviceId}")
    public ResponseEntity<?> getQaDocDevices(@PathVariable Integer deviceId) {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.getQaDocDevices(deviceId), HttpStatus.OK);
    }

    @DeleteMapping("QaDocDevices")
    public ResponseEntity<?> deleteQaDocDevice(@RequestParam Integer docDeviceId) {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.deleteQaDocDevice(docDeviceId), HttpStatus.OK);
    }

    @GetMapping("DeviceInfo")
    public ResponseEntity<?> getDeviceInfo(@RequestParam String controlNo) throws Exception {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.getDeviceInfo(controlNo), HttpStatus.OK);
    }

    @PostMapping(value = "Preview")
    public Object previewFile(@RequestBody QaDocDeviceVo qaDocDeviceVo) {
        String url = qaDocDeviceVo.getRootFolder() + "\\" + qaDocDeviceVo.getControlNo() + "\\" + qaDocDeviceVo.getFileTypeName() + "\\" + qaDocDeviceVo.getFileName();

        File file = new File(url);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("Download")
    public ResponseEntity<byte[]> downloadDocument(@RequestBody QaDocDeviceVo qaDocDeviceVo) {
        String url = qaDocDeviceVo.getRootFolder() + "\\" + qaDocDeviceVo.getControlNo() + "\\" + qaDocDeviceVo.getFileTypeName() + "\\" + qaDocDeviceVo.getFileName();
        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);
    }

    @PostMapping("PrintLabel")
    public ResponseEntity<?> printLabel(@RequestBody List<LabelVo> labelVos, @RequestParam Integer userId ) {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.printLabel(labelVos, userId), HttpStatus.OK);
    }

    @PostMapping("SaveDeviceInfo")
    public ResponseEntity<?> saveDeviceInfo(@RequestBody QaDevice qaDevice) throws Exception {
        return new ResponseEntity<>(this.qaEquipmentMngSvc.saveDeviceInfo(qaDevice), HttpStatus.OK);
    }



}
