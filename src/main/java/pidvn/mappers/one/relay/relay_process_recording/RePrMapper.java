package pidvn.mappers.one.relay.relay_process_recording;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.PurWhRecords;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;

import java.util.List;

@Mapper
public interface RePrMapper {
    List<RequestDto>  getRequests(SearchDto searchDto);
    List<LotDto> getRequestDetail(String requestNo);
}
