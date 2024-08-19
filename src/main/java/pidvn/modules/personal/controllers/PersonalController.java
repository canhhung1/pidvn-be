package pidvn.modules.personal.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserFile;
import pidvn.modules.personal.models.UserInfoVo;
import pidvn.modules.personal.services.PersonalService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("Personal")
public class PersonalController {

    //root path for image files
    private String FILE_PATH_ROOT = "\\\\10.92.152.55\\pvg-data$\\PIDVN-Data\\Public Drive\\IS\\CanhHung\\FDCS\\HR & GA\\UserFiles";

    @Autowired
    private PersonalService personalSvc;

    @GetMapping("UserInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String username) {
        return new ResponseEntity<>(this.personalSvc.getUserInfo(username), HttpStatus.OK);
    }

    @PutMapping("BasicInfo")
    public ResponseEntity<?> updateUserBasicInfo(@RequestBody UserInfoVo userInfoVo) {
        return new ResponseEntity<>(this.personalSvc.updateUserBasicInfo(userInfoVo), HttpStatus.OK);
    }

    @PutMapping("OtherInfo")
    public ResponseEntity<?> updateOtherUserInfo(@RequestBody UserInfoVo userInfoVo) {
        return new ResponseEntity<>(this.personalSvc.updateOtherUserInfo(userInfoVo), HttpStatus.OK);
    }

    @PostMapping("UploadFile")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file, @RequestParam String username, @RequestParam String fileType) throws IOException {
        return new ResponseEntity<>(this.personalSvc.uploadFileV2(file, username, fileType, this.FILE_PATH_ROOT), HttpStatus.OK);
    }

    @GetMapping("UserFileTypes")
    public ResponseEntity<?> getUserFileTypes() {
        return new ResponseEntity<>(this.personalSvc.getUserFileTypes(), HttpStatus.OK);
    }

    @GetMapping("UserFiles")
    public ResponseEntity<?> getUserFiles(@RequestParam String username) {
        return new ResponseEntity<>(this.personalSvc.getUserFiles(username), HttpStatus.OK);
    }

    @PostMapping("OpenUserFile")
    public Object openUserFile(@RequestBody UserFile userFile) {

        File file = new File(userFile.getUrl() + "\\" + userFile.getFileName());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/Images/{username}/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("username") String username, @PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + "\\" + username + "\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


}
