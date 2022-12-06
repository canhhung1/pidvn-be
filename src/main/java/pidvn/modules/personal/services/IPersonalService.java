package pidvn.modules.personal.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserFile;
import pidvn.entities.one.UserFileType;
import pidvn.entities.one.UserInfo;
import pidvn.entities.one.Users;
import pidvn.modules.personal.models.UserFileTypeVo;
import pidvn.modules.personal.models.UserInfoVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPersonalService {

    Map getUserInfo(String username);
    Users updateUserBasicInfo(UserInfoVo userInfoVo);
    UserInfo updateOtherUserInfo(UserInfoVo userInfoVo);
    UserFile uploadFile(MultipartFile file, String username, String fileType) throws IOException;
    UserFile uploadFileV2(MultipartFile file, String username, String fileType, String filePathRoot) throws IOException;
    List<UserFileType> getUserFileTypes();
    List<UserFileTypeVo> getUserFiles(String username);
}
