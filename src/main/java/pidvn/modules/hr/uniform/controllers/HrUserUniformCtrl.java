package pidvn.modules.hr.uniform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.UserUniform;
import pidvn.modules.hr.uniform.models.UserUniformVo;
import pidvn.modules.hr.uniform.services.HrUserUniformSvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("HR/UserUniform")
public class HrUserUniformCtrl {

    @Autowired
    private HrUserUniformSvc hrUserUniformSvc;

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.hrUserUniformSvc.getUsers(), HttpStatus.OK);
    }

    @GetMapping("UserUniforms")
    public ResponseEntity<?> getUserUniforms() {
        return new ResponseEntity<>(this.hrUserUniformSvc.getUserUniforms(), HttpStatus.OK);
    }

    @PostMapping("UserUniform")
    public ResponseEntity<?> saveUserUniform(@RequestBody UserUniform userUniform) {
        return new ResponseEntity<>(this.hrUserUniformSvc.saveUserUniform(userUniform), HttpStatus.OK);
    }

    @GetMapping("UserUniformTypes")
    public ResponseEntity<?> getUserUniformTypes() {
        return new ResponseEntity<>(this.hrUserUniformSvc.getUserUniformTypes(), HttpStatus.OK);
    }

    @PostMapping("DownloadExcelTemplate")
    public ResponseEntity downloadExcelTemplate() throws IOException {
        ByteArrayInputStream inputStream  = this.hrUserUniformSvc.downloadExcelTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ExcelTemplate.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @PostMapping("UploadExcel")
    public ResponseEntity uploadExcel(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity(this.hrUserUniformSvc.uploadExcel(file), HttpStatus.OK);
    }

    @PostMapping("DeleteUserUniforms")
    public ResponseEntity<?> deleteUserUniforms(@RequestBody List<UserUniformVo> userUniformVos) {
        return new ResponseEntity<>(this.hrUserUniformSvc.deleteUserUniforms(userUniformVos), HttpStatus.OK);
    }



}
