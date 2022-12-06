package pidvn.mappers.one.hr.user_info_management;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.user_info_management.models.UserInfoVo;

import java.util.List;

@Mapper
public interface UserInfoMngMapper {
    List<UserInfoVo> getUsers(UserInfoVo searchVo);
    List<UserInfoVo> getBasicUserInfo(String username);
}
