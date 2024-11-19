package pidvn.modules.relay.relay_process_recording.services;

import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface RePrSvc {

    List<RequestDto> getRequests(SearchDto searchDto);
    List<LotDto> getRequestDetail(String requestNo);
    List<LotDto> receiveMaterials(List<LotDto> lots);
    LotDto validateLotReceive(LotDto lotDto);

    Map<String, Object> deleteLotReceived(LotDto lotDto);

    List<LotDto> sendToLineWh(List<LotDto> lots);



}
