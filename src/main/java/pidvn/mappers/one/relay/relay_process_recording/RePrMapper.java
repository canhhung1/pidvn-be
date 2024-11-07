package pidvn.mappers.one.relay.relay_process_recording;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;

import java.util.List;

@Mapper
public interface RePrMapper {
    List<RequestDto>  getRequests();
    List<LotDto> getRequestDetail(String requestNo);
}
