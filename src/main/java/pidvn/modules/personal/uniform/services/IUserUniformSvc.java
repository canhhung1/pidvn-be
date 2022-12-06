package pidvn.modules.personal.uniform.services;

import pidvn.entities.one.UserUniformSize;
import pidvn.modules.personal.uniform.models.UserUniformSizeVo;
import pidvn.modules.personal.uniform.models.UserUniformVo;

import java.util.List;

public interface IUserUniformSvc {
    List<UserUniformSizeVo> getUserUniformSizes(String username);
    List<UserUniformVo>getUserUniforms(String username);
    List<UserUniformSize> saveUserUniformSize(List<UserUniformSize> userUniformSize);

}
