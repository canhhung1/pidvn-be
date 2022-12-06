package pidvn.modules.commons.services;

import pidvn.modules.commons.models.UsersSearchVo;
import pidvn.modules.commons.models.UsersVo;

import java.util.List;

public interface ICommonsService {

    List<UsersVo> getUsers(UsersSearchVo searchVo);
}
