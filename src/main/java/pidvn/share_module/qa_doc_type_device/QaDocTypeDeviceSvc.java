package pidvn.share_module.qa_doc_type_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.QaDocTypeDevice;
import pidvn.repositories.one.QaDocTypeDeviceRepo;

import java.util.List;

@Service
public class QaDocTypeDeviceSvc {

    @Autowired
    private QaDocTypeDeviceRepo qaDocTypeDeviceRepo;

    public List<QaDocTypeDevice> getAll() {
        return this.qaDocTypeDeviceRepo.findAll();
    }


}
