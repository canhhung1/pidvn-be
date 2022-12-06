package pidvn.modules.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.Roles;
import pidvn.modules.administrator.services.RoleService;

@RestController
@RequestMapping("Administrator/Roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(this.roleService.getRoles(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createRole(@RequestBody Roles role) {
        return new ResponseEntity<>(this.roleService.createRole(role), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateRole(@RequestBody Roles role) {
        return new ResponseEntity<>(this.roleService.updateRole(role), HttpStatus.OK);
    }
}
