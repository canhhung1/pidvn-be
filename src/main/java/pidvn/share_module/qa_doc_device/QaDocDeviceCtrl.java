package pidvn.share_module.qa_doc_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.share_module.qa_doc_type_device.QaDocTypeDeviceSvc;

@RestController
@RequestMapping("QaDocDevice")
public class QaDocDeviceCtrl {

    @Autowired
    private QaDocTypeDeviceSvc qaDocTypeDeviceSvc;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.qaDocTypeDeviceSvc.getAll(), HttpStatus.OK);
    }
}
