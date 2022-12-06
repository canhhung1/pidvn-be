package pidvn.modules.administrator.services;

import pidvn.modules.administrator.models.UserSearchVo;
import pidvn.modules.administrator.models.UserVo;

import java.util.List;

public interface IUserService {
    List<UserVo> getUsers(UserSearchVo searchVo);
}
