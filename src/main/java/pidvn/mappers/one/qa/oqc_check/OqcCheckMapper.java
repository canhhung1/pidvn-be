package pidvn.mappers.one.qa.oqc_check;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.qa.oqc_check.models.OqcDataVo;
import pidvn.modules.qa.oqc_check.models.OqcRequestVo;

import java.util.List;

@Mapper
public interface OqcCheckMapper {

    List<OqcDataVo> getOqcMasterData(String reqNo, String qaCard);
    List<OqcRequestVo> getOqcRequests(String reqNo);
}
