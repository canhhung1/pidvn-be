package pidvn.mappers.one.administrator;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.administrator.models.UserSearchVo;
import pidvn.modules.administrator.models.UserVo;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> getUsers(UserSearchVo searchVo);
}
