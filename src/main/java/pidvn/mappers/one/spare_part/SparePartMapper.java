package pidvn.mappers.one.spare_part;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.util.List;

@Mapper
public interface SparePartMapper {
    List<SparePartRecordVo> getSparePartRecords();
}
