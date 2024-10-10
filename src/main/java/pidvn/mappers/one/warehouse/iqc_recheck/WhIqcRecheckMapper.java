package pidvn.mappers.one.warehouse.iqc_recheck;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.warehouse.iqc_recheck.models.LotDto;

import java.util.List;

@Mapper
public interface WhIqcRecheckMapper {
    List<LotDto> getLotGroupsIqcOver6Month();
    List<LotDto> getLotsIqcOver6Month(List<String> lotGroups);
}
