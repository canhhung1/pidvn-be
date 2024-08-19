package pidvn.modules.ie.drawing_control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.ie.drawing_control.dto.ProjectDto;
import pidvn.modules.ie.drawing_control.service.IeDcSvcImpl;

@RestController
@RequestMapping("IE/DrawingControl")
public class IeDcCtl {

    @Autowired
    private IeDcSvcImpl ieDcSvc;


    @GetMapping("Projects")
    public ResponseEntity<?> getProjects() {
        ResponseEntity<?> response = new ResponseEntity<>(ieDcSvc.getProjects(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("Project")
    public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("Projects")
    public ResponseEntity<?> updateProject(ProjectDto projectDto) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }





}
