package pidvn.modules.hr.user_file.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.hr.user_file.models.UserFileVo;
import pidvn.modules.hr.user_file.services.HrUserFileSvc;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("HR/UserFile")
public class HrUserFileCtrl {

    @Autowired
    private HrUserFileSvc hrUserFileSvc;

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.hrUserFileSvc.getUsers(), HttpStatus.OK);
    }

    @GetMapping("Files/{username}")
    public ResponseEntity<?> getUserFiles(@PathVariable String username) {
        return new ResponseEntity<>(this.hrUserFileSvc.getUserFiles(username), HttpStatus.OK);
    }

    @PostMapping("Files")
    public ResponseEntity<?> deleteFile(@RequestBody UserFileVo userFileVo) throws Exception {
        return new ResponseEntity<>(this.hrUserFileSvc.deleteFile(userFileVo),HttpStatus.OK);
    }
}
