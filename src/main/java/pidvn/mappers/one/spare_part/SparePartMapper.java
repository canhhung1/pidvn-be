package pidvn.mappers.one.spare_part;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.spare_part.models.SearchVo;
import pidvn.modules.spare_part.models.SparePartDataChartVo;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface SparePartMapper {
    List<SparePartRecordVo> getSparePartRecords(SearchVo searchVo);


    List<SparePartRecordVo> getSparePartRecordsByStandardPrice(SearchVo searchVo);

    List<SparePartRecordVo> getSparePartInventory(Integer requestId);


    List<SparePartDataChartVo> getSparePartDataChart(SearchVo searchVo);

}
