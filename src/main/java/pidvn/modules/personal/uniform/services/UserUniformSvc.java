package pidvn.modules.personal.uniform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.UserUniformSize;
import pidvn.mappers.one.personal.uniform.UserUniformMapper;
import pidvn.modules.personal.uniform.models.UserUniformSizeVo;
import pidvn.modules.personal.uniform.models.UserUniformVo;
import pidvn.repositories.one.UserUniformSizeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUniformSvc implements IUserUniformSvc {

    @Autowired
    private UserUniformMapper userUniformMapper;

    @Autowired
    private UserUniformSizeRepo userUniformSizeRepo;

    @Override
    public List<UserUniformSizeVo> getUserUniformSizes(String username) {
        return this.userUniformMapper.getUserUniformSizes(username);
    }

    @Override
    public List<UserUniformVo> getUserUniforms(String username) {
        return this.userUniformMapper.getUserUniforms(username);
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager", rollbackFor = Exception.class)
    public List<UserUniformSize> saveUserUniformSize(List<UserUniformSize> userUniformSize) {
        this.userUniformSizeRepo.deleteByUsername(userUniformSize.get(0).getUsername());
        return this.userUniformSizeRepo.saveAll(userUniformSize);
    }
}
