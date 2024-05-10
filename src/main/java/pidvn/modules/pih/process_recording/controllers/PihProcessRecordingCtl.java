package pidvn.modules.pih.process_recording.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.pih.process_recording.models.MaterialSearchVo;
import pidvn.modules.pih.process_recording.models.ScannerVo;
import pidvn.modules.pih.process_recording.services.PihProcessRecordingSvc;

@RestController
@RequestMapping("PIH/ProcessRecording")
public class PihProcessRecordingCtl {

    Logger logger = LoggerFactory.getLogger(PihProcessRecordingCtl.class);

    @Autowired
    private PihProcessRecordingSvc processRecordingSvc;

    @GetMapping("Lines")
    public ResponseEntity<?> getLines(@RequestParam Integer productId) {
        return new ResponseEntity<>(this.processRecordingSvc.getLines(productId), HttpStatus.OK);
    }

    @PostMapping("ScanCoil")
    public ResponseEntity<?> scanCoil(@RequestBody MaterialSearchVo searchVo) {
        return new ResponseEntity<>(this.processRecordingSvc.scanCoil(searchVo), HttpStatus.OK);
    }

    @PostMapping("InsertCoil")
    public ResponseEntity<?> insertCoil(@RequestBody ScannerVo scannerVo) throws Exception {
        boolean isConsumptionMethod = false; // Nếu là true => tính toán Qty theo consumption
        return new ResponseEntity<>(this.processRecordingSvc.insertCoil(scannerVo, isConsumptionMethod), HttpStatus.OK);
    }

    @PostMapping("InsertCoilManual")
    public ResponseEntity<?> insertCoilManual(@RequestBody ScannerVo scannerVo) throws Exception {
        boolean isConsumptionMethod = false; // Nếu là true => tính toán Qty theo consumption
        return new ResponseEntity<>(this.processRecordingSvc.insertCoilManual(scannerVo, isConsumptionMethod), HttpStatus.OK);
    }

    @PostMapping("Materials")
    public ResponseEntity<?> getMaterials(@RequestBody MaterialSearchVo searchVo) {
        return new ResponseEntity<>(this.processRecordingSvc.getMaterials(searchVo), HttpStatus.OK);
    }

    @PostMapping("ChangeLabel")
    public ResponseEntity<?> changeLabel(@RequestBody ScannerVo scannerVo) throws Exception {
        return new ResponseEntity<>(this.processRecordingSvc.changeLabel(scannerVo), HttpStatus.OK);
    }

    @PostMapping("ChangeModel")
    public ResponseEntity<?> changeModel(@RequestBody ScannerVo scannerVo) {
        boolean isConsumptionMethod = false; // Nếu là true => tính toán Qty theo consumption
        return new ResponseEntity<>(this.processRecordingSvc.changeModel(scannerVo, isConsumptionMethod), HttpStatus.OK);
    }

    @GetMapping("PsMasters")
    public ResponseEntity<?> getPsMasters(@RequestParam String model) {
        return new ResponseEntity<>(this.processRecordingSvc.getPsMasters(model), HttpStatus.OK);
    }

    @GetMapping("CheckSetupSaiNVL")
    public ResponseEntity<?> checkSaiNVL(@RequestParam String model, @RequestParam String coil) {
        return new ResponseEntity<>(this.processRecordingSvc.checkSetupSaiNVL(model, coil), HttpStatus.OK);
    }

}
