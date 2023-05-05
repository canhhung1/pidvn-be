package pidvn.share_module.oqc_request_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/OqcRequestStatus")
public class OqcRequestStatusCtrl {

    @Autowired
    private IOqcRequestStatusSvc iOqcRequestStatusSvc;

    @GetMapping("All")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.iOqcRequestStatusSvc.findAll(), HttpStatus.OK);
    }


}
