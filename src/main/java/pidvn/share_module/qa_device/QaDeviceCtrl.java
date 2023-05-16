package pidvn.share_module.qa_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Entity")
public class QaDeviceCtrl {

    @Autowired
    private QaDeviceSvc qaDeviceSvc;

    @GetMapping("QaDevices")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.qaDeviceSvc.getAll(), HttpStatus.OK);
    }

    @GetMapping("QaDevices/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.qaDeviceSvc.getById(id), HttpStatus.OK);
    }

}
