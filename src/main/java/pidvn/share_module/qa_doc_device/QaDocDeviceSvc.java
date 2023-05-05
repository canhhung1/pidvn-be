package pidvn.share_module.qa_doc_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.QaDocDevice;
import pidvn.repositories.one.QaDocDeviceRepo;

import java.util.List;

@Service
public class QaDocDeviceSvc {

    @Autowired
    private QaDocDeviceRepo qaDocDeviceRepo;

    public List<QaDocDevice> getAll() {
        return this.qaDocDeviceRepo.findAll();
    }
}
