package pidvn.modules.hr.user_file.services;

import pidvn.entities.one.UserFile;
import pidvn.modules.hr.user_file.models.UserFileVo;
import pidvn.modules.hr.user_file.models.UserVo;

import java.util.List;

public interface IHrUserFileSvc {
    List<UserVo> getUsers();
    List<UserFileVo> getUserFiles(String username);
    UserFileVo deleteFile(UserFileVo userFileVo) throws Exception;
}
