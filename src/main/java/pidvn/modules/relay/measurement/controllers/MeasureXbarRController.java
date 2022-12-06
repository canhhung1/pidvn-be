package pidvn.modules.relay.measurement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.relay.measurement.models.xbar.MeaSearchVo;
import pidvn.modules.relay.measurement.services.MeaXbarRService;

@RestController
@RequestMapping("Relay/Measurement/XbarR")
public class MeasureXbarRController {

    @Autowired
    private MeaXbarRService meaXbarRService;

    @PostMapping("")
    public ResponseEntity<?> getXbarData(@RequestBody MeaSearchVo searchVo) {
        return new ResponseEntity<>(this.meaXbarRService.getXbarData(searchVo), HttpStatus.OK);
    }



}
