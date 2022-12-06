package pidvn.mappers.one.hr.uniform;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.uniform.models.UserUniformTypeVo;
import pidvn.modules.hr.uniform.models.UserUniformUploadVo;
import pidvn.modules.hr.uniform.models.UserUniformVo;
import pidvn.modules.hr.uniform.models.UserVo;

import java.util.List;

@Mapper
public interface HrUserUniformMapper {
    List<UserUniformVo> getUserUniforms();
    List<UserUniformTypeVo> getUserUniformTypes();
    List<UserVo> getUsers();
    List<UserUniformUploadVo> getUserUniformUploads(String uploadId);
}
