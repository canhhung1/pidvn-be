package pidvn.mappers.one.hr.waste_mng;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.waste_mng.models.WasteDataVo;
import pidvn.modules.hr.waste_mng.models.WasteSearchVo;

import java.util.List;

@Mapper
public interface WasteMngMapper {
    List<WasteDataVo> getWasteMasterData(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailData(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailDataSummary(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailDataSummaryAll(WasteSearchVo searchVo);
}
