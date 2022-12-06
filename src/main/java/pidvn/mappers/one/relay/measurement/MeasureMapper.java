package pidvn.mappers.one.relay.measurement;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.MeaMasterData;
import pidvn.modules.relay.measurement.models.*;

import java.util.List;

@Mapper
public interface MeasureMapper {
    List<MeasureDataVo> getMeasureDataByMasterId(Integer masterId);
    List<MeasureMasterDataVo> getMasterData(MeasureSearchVo searchVo);
    List<MeasureDetailDataVo> getDetailData(MeasureSearchVo searchVo);
    List<MeasureItemVo> getItems();
    List<QaCardVo> getQaCard();
    int quickApprove(List<Integer> masterIds, String approver);
}
