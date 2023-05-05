package pidvn.share_module.oqc_request_status;

import pidvn.entities.one.OqcRequestStatus;

import java.util.List;

public interface IOqcRequestStatusSvc {
    List<OqcRequestStatus> findAll();
}
