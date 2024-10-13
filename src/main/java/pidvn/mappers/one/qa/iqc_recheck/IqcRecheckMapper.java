package pidvn.mappers.one.qa.iqc_recheck;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pidvn.modules.qa.iqc_recheck.models.LotDto;

import java.util.List;

@Mapper
public interface IqcRecheckMapper {

    public List<LotDto> getLotsInRequest(@Param("requestNo") String requestNo, @Param("goodsType") String goodsType);
}
