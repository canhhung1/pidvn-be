package pidvn.modules.relay.relay_process_recording.services;

import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;

import java.util.List;

public interface RePrSvc {

    List<RequestDto> getRequests();
    List<LotDto> getRequestDetail(String requestNo);
}
