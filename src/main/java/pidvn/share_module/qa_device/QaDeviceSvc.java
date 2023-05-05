package pidvn.share_module.qa_device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.QaDevice;
import pidvn.repositories.one.QaDeviceRepo;

import java.util.List;
import java.util.Optional;

@Service
public class QaDeviceSvc {

    @Autowired
    private QaDeviceRepo qaDeviceRepo;

    public List<QaDevice> getAll() {
        return this.qaDeviceRepo.findAll();
    }

    public Optional<QaDevice> getById(Integer id) {
        return this.qaDeviceRepo.findById(id);
    }

    public Optional<QaDevice> getByDeviceNo(String deviceNo) {
        return this.qaDeviceRepo.findByDeviceNo(deviceNo) ;
    }
}
