package pidvn.modules.vr.defect_record.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.DefectRecord;
import pidvn.modules.vr.defect_record.services.VrDefectRecordSvc;

import java.util.List;

@RestController
@RequestMapping("VR/DefectRecord")
public class VrDefectRecordCtl {

    @Autowired
    private VrDefectRecordSvc vrDefectRecordSvc;

    @GetMapping("Process")
    public ResponseEntity<?> getProcessByLine(@RequestParam String line) {
        return new ResponseEntity<>(this.vrDefectRecordSvc.getProcesses(line), HttpStatus.OK);
    }

    @GetMapping("DefectRecords")
    public ResponseEntity<?> getDefectRecords(@RequestParam Integer processId, @RequestParam String lotNo) {
        return new ResponseEntity<>(this.vrDefectRecordSvc.getDefectRecords(processId, lotNo), HttpStatus.OK);
    }

    @PostMapping("DefectRecords")
    public ResponseEntity<?> saveDefectRecords(@RequestBody List<DefectRecord> defectRecords) {
        return new ResponseEntity<>(this.vrDefectRecordSvc.saveDefectRecords(defectRecords), HttpStatus.OK);
    }


}
