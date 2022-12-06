package pidvn.mappers.one.relay.datecode_management;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.datecode_management.models.DateCodeVo;
import pidvn.modules.relay.datecode_management.models.QaCardVo;

import java.util.List;

@Mapper
public interface RelayDateCodeMapper {
    List<DateCodeVo> getDateCodes(String qaCard);
    List<DateCodeVo> getAllDateCode();
    List<QaCardVo> getQaCards();
}
