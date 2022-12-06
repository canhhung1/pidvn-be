package pidvn.modules.relay.material_management.relay_warehouse.services;

import pidvn.entities.one.RelayWhRecords;
import pidvn.modules.relay.material_management.relay_warehouse.models.LotVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.PhieuXuatKhoVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordVo;

import java.util.List;
import java.util.Map;

public interface IReWhSvc {
    List<PhieuXuatKhoVo> getListPXK();
    List<LotVo> getLotsXuatKhoBySlipNo(String slipNo);
    Map receiveLotsFromWH(List<RelayWhRecords> records, String slipNo);
    List<ReWhRecordVo> getRelayWhRecords(ReWhRecordSearchVo searchVo);
    Map sendMaterialToLineWh(List<RelayWhRecords> records);
}
