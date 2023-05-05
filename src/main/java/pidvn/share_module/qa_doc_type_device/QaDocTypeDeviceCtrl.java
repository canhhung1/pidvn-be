package pidvn.share_module.qa_doc_type_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Entity")
public class QaDocTypeDeviceCtrl {

    @Autowired
    private QaDocTypeDeviceSvc qaDocTypeDeviceSvc;

    @GetMapping("QaDocTypeDevices")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.qaDocTypeDeviceSvc.getAll(), HttpStatus.OK);
    }
}
