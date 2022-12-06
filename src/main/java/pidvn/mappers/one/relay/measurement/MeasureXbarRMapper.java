package pidvn.mappers.one.relay.measurement;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.measurement.models.xbar.MeaSearchVo;
import pidvn.modules.relay.measurement.models.xbar.MeaDetailDataVo;

import java.util.List;

@Mapper
public interface MeasureXbarRMapper {

    List<MeaDetailDataVo> getXbarData(MeaSearchVo searchVo);

}
