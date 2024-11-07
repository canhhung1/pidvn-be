package pidvn.modules.relay.relay_process_recording.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.relay.relay_process_recording.RePrMapper;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;

import java.util.List;

@Service
public class RePrSvcImpl implements RePrSvc {

    @Autowired
    private RePrMapper rePrMapper;

    @Override
    public List<RequestDto> getRequests() {
        return this.rePrMapper.getRequests();
    }

    @Override
    public List<LotDto> getRequestDetail(String requestNo) {
        return this.rePrMapper.getRequestDetail(requestNo);
    }
}
