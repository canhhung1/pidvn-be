package pidvn.share_module.relay_date_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/RelayDateCode")
public class RelayDateCodeCtrl {

    @Autowired
    private RelayDateCodeSvc relayDateCodeSvc;

    @GetMapping("All")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.relayDateCodeSvc.findAll(), HttpStatus.OK);
    }

}
