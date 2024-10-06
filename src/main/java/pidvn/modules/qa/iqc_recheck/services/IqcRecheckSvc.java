package pidvn.modules.qa.iqc_recheck.services;

import org.springframework.stereotype.Service;
import pidvn.entities.one.IqcRequest;

import java.util.List;


public interface IqcRecheckSvc {
    List<IqcRequest> getIqcRequests();
}
