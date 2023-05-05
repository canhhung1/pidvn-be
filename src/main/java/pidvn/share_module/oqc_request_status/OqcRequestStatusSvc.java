package pidvn.share_module.oqc_request_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.OqcRequestStatus;
import pidvn.repositories.one.OqcRequestStatusRepo;

import java.util.List;

@Service
public class OqcRequestStatusSvc implements IOqcRequestStatusSvc {

    @Autowired
    private OqcRequestStatusRepo oqcRequestStatusRepo;

    @Override
    public List<OqcRequestStatus> findAll() {
        return this.oqcRequestStatusRepo.findAll();
    }
}
