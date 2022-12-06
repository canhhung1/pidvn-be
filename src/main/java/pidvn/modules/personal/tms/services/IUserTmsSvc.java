package pidvn.modules.personal.tms.services;

import pidvn.modules.personal.tms.models.AttendanceDetailVo;

import java.util.List;

public interface IUserTmsSvc {
    List<AttendanceDetailVo> getAttendanceDetails(String username);
}
