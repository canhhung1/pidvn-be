package pidvn.modules.hr.user_info_management.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserFile;
import pidvn.entities.one.UserFileType;
import pidvn.entities.one.UserInfo;
import pidvn.entities.one.Users;
import pidvn.modules.hr.user_info_management.models.UserInfoVo;
import pidvn.modules.personal.models.UserFileTypeVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IUserInfoMngService {
    List<UserInfoVo> getUsers(UserInfoVo searchVo);
    Map getUserInfo(String username);
    Users updateUserBasicInfo(UserInfoVo userInfoVo);
    UserInfo updateOtherUserInfo(UserInfoVo userInfoVo);
    UserFile uploadFile(MultipartFile file, String username, String fileType) throws IOException;
    List<UserFileType> getUserFileTypes();
    List<UserFileTypeVo> getUserFiles(String username);
    void deleteUserFile(Integer id);
}
