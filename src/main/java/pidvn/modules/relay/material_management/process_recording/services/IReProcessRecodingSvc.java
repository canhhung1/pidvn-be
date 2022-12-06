package pidvn.modules.relay.material_management.process_recording.services;

import pidvn.entities.one.RelayWhRecords;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.process_recording.models.ReWhRecordVo;

import java.util.List;
import java.util.Map;

public interface IReProcessRecodingSvc {
    Map checkScannedLot(ReWhRecordVo reWhRecordVo);
    List<RelayWhRecords> saveAllReWhRecords(List<RelayWhRecords> relayWhRecordsList);
    List<ReWhRecordVo> getRelayWhRecords(ReWhRecordSearchVo searchVo);
}
