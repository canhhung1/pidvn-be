package pidvn.modules.relay.material_management.process_recording.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.entities.one.RelayWhRecords;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordVo;
import pidvn.modules.relay.material_management.process_recording.services.ReProcessRecordingSvc;

import java.util.List;

@RestController
@RequestMapping("Relay/MaterialManagement/RelayProcessRecording")
public class ReProcessRecordingController {

    @Autowired
    private ReProcessRecordingSvc rePrSvc;

    /**
     * Kiểm tra Lot được scan
     * @param reWhRecordVo
     * @return
     */
    @PostMapping("CheckScannedLot")
    public ResponseEntity<?> checkScannedLot(@RequestBody ReWhRecordVo reWhRecordVo) {
        return new ResponseEntity<>(this.rePrSvc.checkScannedLot(reWhRecordVo), HttpStatus.OK);
    }

    @PostMapping("SaveAllReWhRecords")
    public ResponseEntity<?> saveAllReWhRecords(@RequestBody List<RelayWhRecords> relayWhRecordsList) {
        return new ResponseEntity<>(this.rePrSvc.saveAllReWhRecords(relayWhRecordsList), HttpStatus.OK);
    }

    @PostMapping("RelayWhRecords")
    public ResponseEntity<?> getRelayWhRecords(@RequestBody ReWhRecordSearchVo searchVo) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
