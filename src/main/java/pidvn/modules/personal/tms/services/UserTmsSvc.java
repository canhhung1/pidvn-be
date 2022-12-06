package pidvn.modules.personal.tms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.three.personal.tms.UserTmsMapper;
import pidvn.modules.personal.tms.models.AttendanceDetailVo;

import java.util.List;

@Service
public class UserTmsSvc implements IUserTmsSvc {

    @Autowired
    private UserTmsMapper userTmsMapper;

    @Override
    public List<AttendanceDetailVo> getAttendanceDetails(String username) {
        return this.userTmsMapper.getAttendanceDetails(username);
    }
}
