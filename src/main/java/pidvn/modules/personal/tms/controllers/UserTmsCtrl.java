package pidvn.modules.personal.tms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.personal.tms.services.UserTmsSvc;

@RestController
@RequestMapping("Personal/UserTms")
public class UserTmsCtrl {

    @Autowired
    private UserTmsSvc userTmsSvc;

    @GetMapping("AttendanceDetail/{username}")
    public ResponseEntity<?> getAttendanceDetails(@PathVariable String username) {
        return new ResponseEntity<>(this.userTmsSvc.getAttendanceDetails(username), HttpStatus.OK);
    }
}
