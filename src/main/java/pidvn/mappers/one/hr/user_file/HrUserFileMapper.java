package pidvn.mappers.one.hr.user_file;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.user_file.models.UserFileVo;
import pidvn.modules.hr.user_file.models.UserVo;

import java.util.List;

@Mapper
public interface HrUserFileMapper {
    List<UserVo> getUsers();
    List<UserFileVo> getUserFiles(String username);
}
