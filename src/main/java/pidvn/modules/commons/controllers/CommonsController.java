package pidvn.modules.commons.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.commons.models.UsersSearchVo;
import pidvn.modules.commons.services.CommonsService;

@RestController
@RequestMapping("Commons")
public class CommonsController {

    @Autowired
    private CommonsService commonsSvc;

    @PostMapping("Section")
    public ResponseEntity<?> getSection() {
        return null;
    }

    @PostMapping("Subsection")
    public ResponseEntity<?> getSubsection() {
        return null;
    }

    @PostMapping("Position")
    public ResponseEntity<?> getPosition() {
        return null;
    }

    @PostMapping("Users")
    public ResponseEntity<?> getUsers(@RequestBody UsersSearchVo searchVo) {
        return new ResponseEntity<>(this.commonsSvc.getUsers(searchVo), HttpStatus.OK);
    }

}
