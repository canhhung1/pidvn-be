package pidvn.modules.relay.relay_process_recording.services;

import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;

import java.util.List;

public interface RePrSvc {

    List<RequestDto> getRequests(SearchDto searchDto);
    List<LotDto> getRequestDetail(String requestNo);

    List<LotDto> receiveMaterials(List<LotDto> lots);

    LotDto validateLotReceive(LotDto lotDto);
}
