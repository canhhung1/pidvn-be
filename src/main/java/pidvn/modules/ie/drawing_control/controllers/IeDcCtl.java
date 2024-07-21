package pidvn.modules.ie.drawing_control.controllers;

import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectProgressDto;
import pidvn.modules.ie.drawing_control.models.SearchDto;
import pidvn.modules.ie.drawing_control.services.IeDcSvc;

@RestController
@RequestMapping("IE/DrawingControl")
public class IeDcCtl {


    @Autowired
    private IeDcSvc ieDcSvc;


    /**
     * Lấy danh sách project
     * @return List
     */
    @PostMapping("Projects")
    public ResponseEntity<?> getProjects(SearchDto searchDto) {
        return new ResponseEntity<>(this.ieDcSvc.getProjects(searchDto), HttpStatus.OK);
    }

    /**
     * Lấy thông tin detail của project
     * @param projectId projectId
     * @return
     */
    @GetMapping("Project/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer projectId) {
        return new ResponseEntity<>(this.ieDcSvc.getProjectById(projectId), HttpStatus.OK);
    }

    @PostMapping("Project")
    public ResponseEntity<?> insertProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(this.ieDcSvc.insertProject(projectDto), HttpStatus.OK);
    }

    @PutMapping("Project")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(this.ieDcSvc.updateProject(projectDto), HttpStatus.OK);
    }

    @GetMapping("ProjectTypes")
    public ResponseEntity<?> getProjectTypes() {
        return new ResponseEntity<>(this.ieDcSvc.getProjectTypes(), HttpStatus.OK);
    }

    @GetMapping("ProjectProgresses")
    public ResponseEntity<?> getProjectProgresses(@RequestParam Integer projectId) {
        return new ResponseEntity<>(this.ieDcSvc.getProjectProgresses(projectId), HttpStatus.OK);
    }

    @PutMapping("ProjectProgress")
    public ResponseEntity<?> updateProjectProgress(@RequestBody ProjectProgressDto projectProgressDto) {
        return new ResponseEntity<>(this.ieDcSvc.updateProjectProgress(projectProgressDto), HttpStatus.OK);
    }

//    @GetMapping("ProjectProgressDetail")
//    public ResponseEntity<?> getProjectProgressDetail(@RequestParam Integer projectId, @RequestParam Integer projectProgressId) {
//        return new ResponseEntity<>(this.ieDcSvc.getProjectProgressDetail(projectId,projectProgressId), HttpStatus.OK);
//    }

}
