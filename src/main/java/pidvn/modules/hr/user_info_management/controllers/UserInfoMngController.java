package pidvn.modules.hr.user_info_management.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserFile;
import pidvn.modules.hr.user_info_management.models.UserInfoVo;
import pidvn.modules.hr.user_info_management.services.UserInfoMngService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("HR/UserInfoManagement")
public class UserInfoMngController {

    @Autowired
    private UserInfoMngService userInfoMngSvc;

    @PostMapping("Users")
    public ResponseEntity<?> getUsers(@RequestBody UserInfoVo searchVo) {
        return new ResponseEntity<>(this.userInfoMngSvc.getUsers(searchVo), HttpStatus.OK);
    }

    @GetMapping("UserInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String username) {
        return new ResponseEntity<>(this.userInfoMngSvc.getUserInfo(username), HttpStatus.OK);
    }

    @PutMapping("BasicInfo")
    public ResponseEntity<?> updateUserBasicInfo(@RequestBody UserInfoVo userInfoVo) {
        return new ResponseEntity<>(this.userInfoMngSvc.updateUserBasicInfo(userInfoVo), HttpStatus.OK);
    }

    @PutMapping("OtherInfo")
    public ResponseEntity<?> updateOtherUserInfo(@RequestBody UserInfoVo userInfoVo) {
        return new ResponseEntity<>(this.userInfoMngSvc.updateOtherUserInfo(userInfoVo), HttpStatus.OK);
    }

    @PostMapping("UploadFile")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file, @RequestParam String username, @RequestParam String fileType) throws IOException {
        return new ResponseEntity<>(this.userInfoMngSvc.uploadFile(file, username,fileType), HttpStatus.OK);
    }

    @GetMapping("UserFileTypes")
    public ResponseEntity<?> getUserFileTypes() {
        return new ResponseEntity<>(this.userInfoMngSvc.getUserFileTypes(), HttpStatus.OK);
    }

    @GetMapping("UserFiles")
    public ResponseEntity<?> getUserFiles(@RequestParam String username) {
        return new ResponseEntity<>(this.userInfoMngSvc.getUserFiles(username), HttpStatus.OK);
    }

    @PostMapping("OpenUserFile")
    public Object openUserFile(@RequestBody UserFile userFile) {

        String uploadDir = "../FileRepository/pidvn-be/UserFiles";
        File file = new File(".."+userFile.getUrl());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("DeleteUserFile")
    public ResponseEntity<?> deleteUserFile(@RequestParam Integer id) {
        this.userInfoMngSvc.deleteUserFile(id);
        Map result = new HashMap();
        result.put("response", "Delete success Id: " + id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
