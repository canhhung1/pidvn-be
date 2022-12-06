package pidvn.mappers.one.personal.uniform;

import pidvn.modules.personal.uniform.models.UserUniformSizeVo;
import pidvn.modules.personal.uniform.models.UserUniformVo;

import java.util.List;

public interface UserUniformMapper {
    List<UserUniformSizeVo> getUserUniformSizes(String username);
    List<UserUniformVo>getUserUniforms(String username);
}
