package pidvn.mappers.one.relay.material_management.relay_warehouse;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.material_management.relay_warehouse.models.PhieuXuatKhoVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.LotVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordVo;

import java.util.List;

@Mapper
public interface RelayWarehouseMapper {
    List<PhieuXuatKhoVo> getListPXK();
    List<LotVo> getLotsXuatKhoBySlipNo(String slipNo);
    List<ReWhRecordVo> getRelayWhRecords(ReWhRecordSearchVo searchVo);
    List<ReWhRecordVo> getNewestRelayWhRecords(ReWhRecordSearchVo searchVo);
}
