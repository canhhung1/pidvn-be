package pidvn.modules.hr.uniform.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserUniform;
import pidvn.modules.hr.uniform.models.UserUniformTypeVo;
import pidvn.modules.hr.uniform.models.UserUniformVo;
import pidvn.modules.hr.uniform.models.UserVo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHrUserUniformSvc {

    List<UserUniformVo> getUserUniforms();
    UserUniform saveUserUniform(UserUniform userUniform);
    List<UserUniformTypeVo> getUserUniformTypes();
    List<UserVo> getUsers();
    Map uploadExcel(MultipartFile file) throws IOException;
    ByteArrayInputStream downloadExcelTemplate() throws IOException;
    List<UserUniformVo> deleteUserUniforms(List<UserUniformVo> userUniformVos);

}
