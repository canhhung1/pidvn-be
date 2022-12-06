package pidvn.modules.relay.datecode_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.RelayDateCode;
import pidvn.modules.relay.datecode_management.services.RelayDateCodeSvc;

@RestController
@RequestMapping("Relay/DateCodeMng")
public class RelayDateCodeCtl {

    @Autowired
    private RelayDateCodeSvc relayDateCodeSvc;

    @GetMapping("DateCodes")
    public ResponseEntity<?> getDateCodes(@RequestParam String qaCard) {
        return new ResponseEntity<>(this.relayDateCodeSvc.getDateCodes(qaCard), HttpStatus.OK);
    }

    @GetMapping("GetAllDateCode")
    public  ResponseEntity<?> getAllDateCode() {
        return new ResponseEntity<>(this.relayDateCodeSvc.getAllDateCode(), HttpStatus.OK);
    }

    @PostMapping("DateCode")
    public ResponseEntity<?> createDateCode(@RequestBody RelayDateCode dateCode) throws Exception {
        return new ResponseEntity<>(this.relayDateCodeSvc.createDateCode(dateCode), HttpStatus.OK);
    }

    @DeleteMapping("DateCode/{id}")
    public ResponseEntity<?> deleteDateCode(@PathVariable Integer id) {
        return new ResponseEntity<>(this.relayDateCodeSvc.deleteDateCode(id), HttpStatus.OK);
    }

    @GetMapping("QACards")
    public ResponseEntity<?> getQACards() {
        return new ResponseEntity<>(this.relayDateCodeSvc.getQaCards(), HttpStatus.OK);
    }

}
