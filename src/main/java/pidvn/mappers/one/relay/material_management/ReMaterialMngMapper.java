package pidvn.mappers.one.relay.material_management;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.material_management.models.*;

import java.util.List;

@Mapper
public interface ReMaterialMngMapper {
    List<PurWhRecordVo> getPurWhRecords(PurWhRecordSearchVo searchVo);
    List<PurWhRecordVo> getLotsBySlipNo(String slipNo, String groupBy);
    List<PurWhRecordVo> getActualQtyLineWh(PurWhRecordSearchVo searchVo);
    List<PurWhRecordVo> materialTraceability(SearchVo searchVo);

    // Version3
    List<MaterialVo> getMaterials(MaterialSearchVo searchVo);

}
