package pidvn.modules.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.administrator.models.UserSearchVo;
import pidvn.modules.administrator.services.UserService;

@RestController
@RequestMapping("Administrator/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> getUsers(@RequestBody UserSearchVo searchVo) {
        return new ResponseEntity<>(this.userService.getUsers(searchVo), HttpStatus.OK);
    }
}
