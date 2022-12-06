package pidvn.mappers.three.personal.tms;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.personal.tms.models.AttendanceDetailVo;

import java.util.List;

@Mapper
public interface UserTmsMapper {
    List<AttendanceDetailVo> getAttendanceDetails(String username);
}
