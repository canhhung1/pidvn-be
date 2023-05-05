package pidvn.modules.packing.oqc_request.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.packing.oqc_request.models.OqcRequestVo;
import pidvn.modules.packing.oqc_request.models.SearchVo;
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

    @PutMapping("Request")
    public ResponseEntity<?> updateOqcRequest(@RequestBody OqcRequestVo oqcRequestVo) {
        return new ResponseEntity<>(this.packingOqcRequestSvc.updateOqcRequest(oqcRequestVo), HttpStatus.OK);
    }

    @PostMapping("DataSummary")
    public ResponseEntity<?> summaryData(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.packingOqcRequestSvc.summaryData(searchVo), HttpStatus.OK);
    }


}
