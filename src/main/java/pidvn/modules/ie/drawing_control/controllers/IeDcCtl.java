package pidvn.modules.ie.drawing_control.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.modules.ie.drawing_control.services.IeDcSvc;
import reactor.util.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @GetMapping("Drawings")
    public ResponseEntity<?> getDrawings(@RequestParam @Nullable Integer projectId) {
        return new ResponseEntity<>(this.ieDcSvc.getDrawings(projectId), HttpStatus.OK);
    }

    @PostMapping("Drawing")
    public ResponseEntity<?> insertDrawing(@RequestBody DrawingDto drawingDto) {
        return new ResponseEntity<>(this.ieDcSvc.saveDrawing(drawingDto), HttpStatus.OK);
    }

    @PutMapping("Drawing")
    public ResponseEntity<?> updateDrawing(@RequestBody DrawingDto drawingDto) {
        return new ResponseEntity<>(this.ieDcSvc.saveDrawing(drawingDto), HttpStatus.OK);
    }



    @PostMapping("UploadDrawing")
    public ResponseEntity<?> uploadDrawing(@RequestBody MultipartFile file, @RequestParam String projectNo,@RequestParam String drawingName) {
        return new ResponseEntity<>(this.ieDcSvc.uploadDrawing(file, projectNo,drawingName), HttpStatus.OK);
    }


    @PostMapping("UploadDrawingTreeList")
    public ResponseEntity<?> uploadDrawingTreeList(@RequestBody MultipartFile file, @RequestParam Integer projectId) throws IOException {
        return new ResponseEntity<>(this.ieDcSvc.uploadDrawingTreeList(file, projectId), HttpStatus.OK);
    }





    /**
     * Preview nếu là file pdf
     * @param searchDto
     * @return
     */
    @PostMapping(value = "DrawingPreview")
    public Object previewFile(@RequestBody SearchDto searchDto) {

        File file = new File(searchDto.getUrl());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("ProjectActivities")
    public ResponseEntity<?> getProjectActivities(@RequestParam Integer projectId) {
        return new ResponseEntity<>(this.ieDcSvc.getProjectActivities(projectId), HttpStatus.OK);
    }

    @PostMapping("ProjectActivity")
    public ResponseEntity<?> insertProjectActivity(@RequestParam("file") @Nullable MultipartFile file, @RequestPart("projectActivityDto") ProjectActivityDto projectActivityDto) {
        return new ResponseEntity<>(this.ieDcSvc.insertProjectActivity(file, projectActivityDto), HttpStatus.OK);
    }

}
