package pidvn.modules.commons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.commons.CommonsMapper;
import pidvn.modules.commons.models.UsersSearchVo;
import pidvn.modules.commons.models.UsersVo;

import java.util.List;

@Service
public class CommonsService implements ICommonsService {

    @Autowired
    private CommonsMapper commonsMapper;

    @Override
    public List<UsersVo> getUsers(UsersSearchVo searchVo) {
        return this.commonsMapper.getUsers(searchVo);
    }
}
