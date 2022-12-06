package pidvn.mappers.one.hr.e_leave;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.schedule_task.e_leave.models.EleaveVo;
import pidvn.modules.schedule_task.e_leave.models.EmailVo;

import java.util.List;

@Mapper
public interface ELeaveMapper {

    List<EmailVo> getEmailInform();
    List<EleaveVo> getUserMissInOut();
    List<EmailVo> getEmailRejectRequest();
}
