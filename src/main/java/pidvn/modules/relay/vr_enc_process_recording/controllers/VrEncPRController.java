package pidvn.modules.relay.vr_enc_process_recording.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.relay.vr_enc_process_recording.models.MaterialVo;
import pidvn.modules.relay.vr_enc_process_recording.models.QaCardVo;
import pidvn.modules.relay.vr_enc_process_recording.models.ScannerVo;
import pidvn.modules.relay.vr_enc_process_recording.models.SearchVo;
import pidvn.modules.relay.vr_enc_process_recording.services.VrEncPRService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("Relay/VrEncProcessRecording")
public class VrEncPRController {

    @Autowired
    private VrEncPRService vrEncPRSvc;

    /**
     * Kiểm tra Model được tạo ra bởi những NVL nào
     * @param model
     * @return
     */
    @GetMapping("Parts")
    public ResponseEntity<?> getPartsByModel(@RequestParam String model) {
        return new ResponseEntity<>(this.vrEncPRSvc.getPartsByModel(model), HttpStatus.OK);
    }

    @GetMapping("Models")
    public ResponseEntity<?> getModels() {
        return new ResponseEntity<>(this.vrEncPRSvc.getModels(), HttpStatus.OK);
    }

    @GetMapping("Shifts")
    public ResponseEntity<?> getShifts() {
        return new ResponseEntity<>(this.vrEncPRSvc.getShifts(), HttpStatus.OK);
    }

    @PostMapping("QaCards")
    public ResponseEntity<?> getQaCards(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.vrEncPRSvc.getQaCards(searchVo), HttpStatus.OK);
    }

    @PostMapping("CreateQaCard")
    public ResponseEntity<?> createQaCard(@RequestBody QaCardVo qaCardVo) {
        return new ResponseEntity<>(this.vrEncPRSvc.createQaCard(qaCardVo), HttpStatus.OK);
    }

    @GetMapping("Processes")
    public ResponseEntity<?> getProcesses(@RequestParam String productTypeName) {
        return new ResponseEntity<>(this.vrEncPRSvc.getProcesses(productTypeName), HttpStatus.OK);
    }

    @GetMapping("ProcessesVer2")
    public ResponseEntity<?> getProcessesVer2(@RequestParam String line) {
        return new ResponseEntity<>(this.vrEncPRSvc.getProcessesVer2(line), HttpStatus.OK);
    }

    @GetMapping("Lines")
    public ResponseEntity<?> getLines(@RequestParam Integer productId) {
        return new ResponseEntity<>(this.vrEncPRSvc.getLines(productId), HttpStatus.OK);
    }

    @PostMapping("Materials")
    public ResponseEntity<?> getMaterials(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.vrEncPRSvc.getMaterials(searchVo), HttpStatus.OK);
    }

    @PostMapping("ScanLabel")
    public ResponseEntity<?> scanLabel(@RequestBody ScannerVo scannerVo) throws Exception {
        return new ResponseEntity<>(this.vrEncPRSvc.scanLabel(scannerVo), HttpStatus.OK);
    }

    @PostMapping("InsertMaterials")
    public ResponseEntity<?> insertMaterials(@RequestBody List<MaterialVo> materialVos) {
        return new ResponseEntity<>(this.vrEncPRSvc.insertMaterials(materialVos), HttpStatus.OK);
    }

    @PostMapping("UpdateMaterial")
    public ResponseEntity<?> updateMaterial(@RequestBody MaterialVo materialVo) throws Exception {
        return new ResponseEntity<>(this.vrEncPRSvc.updateMaterial(materialVo), HttpStatus.OK);
    }

    @PostMapping("DownloadQaCard")
    public ResponseEntity downloadQaCard(@RequestBody SearchVo searchVo) throws IOException {

        ByteArrayInputStream inputStream  = this.vrEncPRSvc.downloadQaCard(searchVo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Export.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @PostMapping("Traceability")
    public ResponseEntity<?> traceability(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.vrEncPRSvc.traceability(searchVo), HttpStatus.OK);
    }

}
