package pidvn.modules.personal.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.personal.PersonalMapper;
import pidvn.modules.personal.models.UserFileTypeVo;
import pidvn.modules.personal.models.UserInfoVo;
import pidvn.modules.relay.measurement.utils.FileUploadUtil;
import pidvn.repositories.one.*;

import java.io.IOException;
import java.util.*;

@Service
public class PersonalService implements IPersonalService {

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private SectionRepo sectionRepo;

    @Autowired
    private SubsectionRepo subsectionRepo;

    @Autowired
    private UserFileRepo userFileRepo;

    @Autowired
    private UserFileTypeRepo userFileTypeRepo;

    @Override
    public Map getUserInfo(String username) {

        List<UserInfoVo> userBasicInfo = this.personalMapper.getBasicUserInfo(username);
        List<Position> positions = this.positionRepo.findAll();
        List<Section> sections = this.sectionRepo.findAll();
        List<Subsection> subsections = this.subsectionRepo.findAll();
        UserInfo userOtherInfo = this.userInfoRepo.findByUsername(username);

        Map map = new HashMap();
        map.put("userBasicInfo", userBasicInfo.get(0));
        map.put("userOtherInfo", userOtherInfo == null ? new UserInfo() : userOtherInfo);
        map.put("positions", positions);
        map.put("sections", sections);
        map.put("subsections", subsections);

        return map;
    }

    @Override
    public Users updateUserBasicInfo(UserInfoVo userInfoVo) {

        Users user = this.usersRepo.findByUsername(userInfoVo.getUsername());

        user.setName(userInfoVo.getName());
        user.setEmail(userInfoVo.getEmail());
        user.setDob(userInfoVo.getDob());
        user.setJoinDate(userInfoVo.getJoinDate());
        user.setSubsectionId(userInfoVo.getSubsectionId());
        user.setPositionId(userInfoVo.getPositionId());

        return this.usersRepo.save(user);
    }

    @Override
    public UserInfo updateOtherUserInfo(UserInfoVo infoVo) {

        UserInfo userInfo = this.userInfoRepo.findByUsername(infoVo.getUsername());

        if (userInfo != null) {
            userInfo.setUsername(infoVo.getUsername());
            userInfo.setSoCccd(infoVo.getSoCccd());
            userInfo.setNgaycapCccd(infoVo.getNgaycapCccd());
            userInfo.setNoicapCccd(infoVo.getNoicapCccd());
            userInfo.setSoPassport(infoVo.getSoPassport());
            userInfo.setNgaycapPassport(infoVo.getNgaycapPassport());
            userInfo.setNgayhethanPassport(infoVo.getNgayhethanPassport());
            userInfo.setNoicapPassport(infoVo.getNoicapPassport());

            userInfo.setSoCmnd(infoVo.getSoCmnd());
            userInfo.setNgaycapTcc(infoVo.getNgaycapTcc());
            userInfo.setNoicapTcc(infoVo.getNoicapTcc());
            userInfo.setNgayhethanTcc(infoVo.getNgayhethanTcc());
            userInfo.setMaritalStatus(infoVo.getMaritalStatus());
            userInfo.setDiachiThuongtru(infoVo.getDiachiThuongtru());
            userInfo.setDiachiTamtru(infoVo.getDiachiTamtru());

            userInfo.setNoisinh(infoVo.getNoisinh());
            userInfo.setNguyenquan(infoVo.getNguyenquan());
            userInfo.setNationality(infoVo.getNationality());
            userInfo.setDantoc(infoVo.getDantoc());
            userInfo.setTongiao(infoVo.getTongiao());
            userInfo.setTrinhdoHocvan(infoVo.getTrinhdoHocvan());
            userInfo.setTrinhdoVanhoa(infoVo.getTrinhdoVanhoa());
            userInfo.setEmail(infoVo.getEmail());
            userInfo.setPhone(infoVo.getPhone());
            userInfo.setZalo(infoVo.getZalo());
            userInfo.setFacebook(infoVo.getFacebook());
            return this.userInfoRepo.save(userInfo);
        }

        userInfo = new UserInfo();
        userInfo.setUsername(infoVo.getUsername());
        userInfo.setSoCccd(infoVo.getSoCccd());
        userInfo.setNgaycapCccd(infoVo.getNgaycapCccd());
        userInfo.setNoicapCccd(infoVo.getNoicapCccd());
        userInfo.setSoPassport(infoVo.getSoPassport());
        userInfo.setNgaycapPassport(infoVo.getNgaycapPassport());
        userInfo.setNgayhethanPassport(infoVo.getNgayhethanPassport());
        userInfo.setNoicapPassport(infoVo.getNoicapPassport());

        userInfo.setSoCmnd(infoVo.getSoCmnd());
        userInfo.setNgaycapTcc(infoVo.getNgaycapTcc());
        userInfo.setNoicapTcc(infoVo.getNoicapTcc());
        userInfo.setNgayhethanTcc(infoVo.getNgayhethanTcc());
        userInfo.setMaritalStatus(infoVo.getMaritalStatus());
        userInfo.setDiachiThuongtru(infoVo.getDiachiThuongtru());
        userInfo.setDiachiTamtru(infoVo.getDiachiTamtru());

        userInfo.setNoisinh(infoVo.getNoisinh());
        userInfo.setNguyenquan(infoVo.getNguyenquan());
        userInfo.setNationality(infoVo.getNationality());
        userInfo.setDantoc(infoVo.getDantoc());
        userInfo.setTongiao(infoVo.getTongiao());
        userInfo.setTrinhdoHocvan(infoVo.getTrinhdoHocvan());
        userInfo.setTrinhdoVanhoa(infoVo.getTrinhdoVanhoa());
        userInfo.setEmail(infoVo.getEmail());
        userInfo.setPhone(infoVo.getPhone());
        userInfo.setZalo(infoVo.getZalo());
        userInfo.setFacebook(infoVo.getFacebook());
        return this.userInfoRepo.save(userInfo);
    }

    @Override
    public UserFile uploadFile(MultipartFile file, String username, String fileType) throws IOException {

        String fileName = username + "_" + fileType + "_" + UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String uploadDir = "../FileRepository/pidvn-be/UserFiles/";

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        UserFile userFile = new UserFile();
        userFile.setUsername(username);
        userFile.setFileType(fileType);
        userFile.setFileFormat(file.getContentType());
        userFile.setUrl(uploadDir.replaceAll("\\.","") + fileName);

        return this.userFileRepo.save(userFile);
    }

    @Override
    public UserFile uploadFileV2(MultipartFile file, String username, String fileType, String filePathRoot) throws IOException {

        String fileName = fileType + "_" + UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String uploadDir = filePathRoot + "\\" + username + "\\";
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        UserFile userFile = new UserFile();
        userFile.setUsername(username);
        userFile.setFileType(fileType);
        userFile.setFileFormat(file.getContentType());
        userFile.setUrl(uploadDir);
        userFile.setFileName(fileName);

        return this.userFileRepo.save(userFile);
    }

    @Override
    public List<UserFileType> getUserFileTypes() {
        return this.userFileTypeRepo.findAll();
    }

    @Override
    public List<UserFileTypeVo> getUserFiles(String username) {

        List<UserFileType> fileTypes = this.userFileTypeRepo.findAll();
        List<UserFile> files = this.userFileRepo.findByUsername(username);

        List<UserFileTypeVo> result = new ArrayList<>();

        for (UserFileType fileType : fileTypes) {
            UserFileTypeVo obj = new UserFileTypeVo();
            List<UserFile> listFile = new ArrayList<>();

            for (UserFile file: files) {
                if (fileType.getCode().equals(file.getFileType())) {
                    listFile.add(file);
                }
            }

            obj.setName(fileType.getName());
            obj.setFiles(listFile);
            result.add(obj);
        }

        return result;
    }

}
