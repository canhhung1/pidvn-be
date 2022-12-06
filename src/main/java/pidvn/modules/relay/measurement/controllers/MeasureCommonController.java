package pidvn.modules.relay.measurement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.relay.measurement.services.CommonService;

@RestController
@RequestMapping("Relay/Measurement/Commons")
public class MeasureCommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("Reasons")
    public ResponseEntity<?> getReasons() {
        return new ResponseEntity<>(this.commonService.getReasons(), HttpStatus.OK);
    }

    @GetMapping("Models")
    public ResponseEntity<?> getModels() {
        return new ResponseEntity<>(this.commonService.getModels(), HttpStatus.OK);
    }

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.commonService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("QaCards")
    public ResponseEntity<?> getQaCards() {
        return new ResponseEntity<>(this.commonService.getQaCards(), HttpStatus.OK);
    }
}
