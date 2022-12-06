package pidvn.mappers.one.personal;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.personal.models.UserInfoVo;

import java.util.List;

@Mapper
public interface PersonalMapper {
    List<UserInfoVo> getBasicUserInfo(String username);
}
