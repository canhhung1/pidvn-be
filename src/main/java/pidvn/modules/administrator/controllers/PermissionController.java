package pidvn.modules.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.Permissions;
import pidvn.modules.administrator.services.PermissionService;

@RestController
@RequestMapping("Administrator/Permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionSvc;

    @GetMapping("")
    public ResponseEntity<?> getPermissions() {
        return new ResponseEntity<>(this.permissionSvc.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createPermission(@RequestBody Permissions permission) {
        return new ResponseEntity<>(this.permissionSvc.createPermission(permission), HttpStatus.OK);
    }
}
