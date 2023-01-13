package pidvn.modules.packing.oqc_request.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.packing.oqc_request.models.OqcRequestVo;
import pidvn.modules.packing.oqc_request.services.PackingOqcRequestSvc;


@RestController
@RequestMapping("Packing/OqcRequest")
public class PackingOqcRequestCtrl {

    @Autowired
    private PackingOqcRequestSvc packingOqcRequestSvc;

    @PostMapping("Request")
    public ResponseEntity<?> createOqcRequest(@RequestBody OqcRequestVo oqcRequestVo) {
        return new ResponseEntity<>(this.packingOqcRequestSvc.createOqcRequest(oqcRequestVo), HttpStatus.OK);
    }

}
