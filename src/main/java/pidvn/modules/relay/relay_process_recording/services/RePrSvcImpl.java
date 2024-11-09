package pidvn.modules.relay.relay_process_recording.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Lots;
import pidvn.entities.one.PurWhRecords;
import pidvn.mappers.one.relay.relay_process_recording.RePrMapper;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.PurWhRecordsRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RePrSvcImpl implements RePrSvc {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RePrMapper rePrMapper;

    @Autowired
    private PurWhRecordsRepo purWhRecordsRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Override
    public List<RequestDto> getRequests(SearchDto searchDto) {
        return this.rePrMapper.getRequests(searchDto);
    }

    @Override
    public List<LotDto> getRequestDetail(String requestNo) {
        return this.rePrMapper.getRequestDetail(requestNo);
    }

    /**
     * Nhận NVL từ kho WH
     * @param lots danh sach NVL
     * @return
     */
    @Override
    public List<LotDto> receiveMaterials(List<LotDto> lots) {
        List<PurWhRecords> data = lots.stream().map(item -> modelMapper.map(item, PurWhRecords.class)).collect(Collectors.toList());
        this.purWhRecordsRepo.saveAll(data);
        return lots;
    }

    @Override
    public LotDto validateLotReceive(LotDto lotDto) {
        Lots obj = this.lotsRepo.findByLotNo(lotDto.getLotNo());
        LotDto lot = this.modelMapper.map(obj, LotDto.class);
        lot.setReqNo(lotDto.getReqNo());
        lot.setRecordType(lotDto.getRecordType());
        lot.setFlag(lotDto.getFlag());
        lot.setRemainQty(obj.getQty());
        return lot;
    }


}
