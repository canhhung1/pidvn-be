package pidvn.modules.ie.drawing_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.ie.drawing_management.services.DrawingMngSvc;

@RestController
@RequestMapping("IE/DrawingManagement")
public class DrawingMngCtl {


    @Autowired
    private DrawingMngSvc drawingMngSvc;

    @GetMapping("Projects")
    public ResponseEntity<?> getProjects() {
        return new ResponseEntity<>(this.drawingMngSvc.getProjects(), HttpStatus.OK);
    }




}
