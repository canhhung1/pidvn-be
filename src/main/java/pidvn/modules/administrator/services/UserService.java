package pidvn.modules.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.administrator.UserMapper;
import pidvn.modules.administrator.models.UserSearchVo;
import pidvn.modules.administrator.models.UserVo;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getUsers(UserSearchVo searchVo) {
        return this.userMapper.getUsers(searchVo);
    }
}
