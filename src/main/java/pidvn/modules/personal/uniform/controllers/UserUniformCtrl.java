package pidvn.modules.personal.uniform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.UserUniformSize;
import pidvn.modules.personal.uniform.services.UserUniformSvc;

import java.util.List;

@RestController
@RequestMapping("Personal/UserUniform")
public class UserUniformCtrl {

    @Autowired
    private UserUniformSvc userUniformSvc;

    @GetMapping("UserUniformSizes")
    public ResponseEntity<?> getUserUniformSizes(@RequestParam String username) {
        return new ResponseEntity<>(this.userUniformSvc.getUserUniformSizes(username), HttpStatus.OK);
    }

    @GetMapping("UserUniforms")
    public ResponseEntity<?> getUserUniforms(@RequestParam String username) {
        return new ResponseEntity<>(this.userUniformSvc.getUserUniforms(username), HttpStatus.OK);
    }

    @PostMapping("UserUniformSize")
    private ResponseEntity<?> saveUserUniformSize(@RequestBody List<UserUniformSize> userUniformSizes) {
        return new ResponseEntity<>(this.userUniformSvc.saveUserUniformSize(userUniformSizes), HttpStatus.OK);
    }



}
