package pidvn.modules.ie.drawing_control.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.modules.ie.drawing_control.services.IeDcSvc;
import reactor.util.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("IE/DrawingControl")
public class IeDcCtl {


    @Autowired
    private IeDcSvc ieDcSvc;

    @GetMapping("PersonInCharges")
    public ResponseEntity<?> getPersonInCharges() {
        int[] subsections = {13, 32};
        List<Integer> subsectionIds = Arrays.stream(subsections)
                .boxed()
                .collect(Collectors.toList());
        return new ResponseEntity<>(this.ieDcSvc.getPersonInCharges(subsectionIds),HttpStatus.OK);
    }

    @GetMapping("Products")
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<>(this.ieDcSvc.getProducts(), HttpStatus.OK);
    }

    /**
     * Lấy danh sách project
     * @return List
     */
    @PostMapping("Projects")
    public ResponseEntity<?> getProjects(@RequestBody SearchDto searchDto) {
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

    /**
     * Lấy danh sách Progress theo ProjectType
     * @return
     */
    @GetMapping("ProgressesByProjectType")
    public ResponseEntity<?> getProgressesByProjectType(@RequestParam Integer projectTypeId) {
        return new ResponseEntity<>(this.ieDcSvc.getProgressesByProjectType(projectTypeId), HttpStatus.OK);
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



    @PostMapping("UploadDrawingFile")
    public ResponseEntity<?> uploadDrawingFile(@RequestParam("files") MultipartFile[] files, @RequestParam String projectNo) {
        return new ResponseEntity<>(this.ieDcSvc.uploadDrawingFile(files, projectNo), HttpStatus.OK);
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
    public ResponseEntity<byte[]> previewFile(@RequestBody SearchDto searchDto) {

        File file = new File(searchDto.getUrl());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileContent = IOUtils.toByteArray(fileInputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", file.getName());

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("ProjectActivities")
    public ResponseEntity<?> getProjectActivities(@RequestParam Integer projectId) {
        return new ResponseEntity<>(this.ieDcSvc.getProjectActivities(projectId), HttpStatus.OK);
    }

    @PostMapping("ProjectActivity")
    public ResponseEntity<?> insertProjectActivity(@RequestParam("file") @Nullable MultipartFile file, @RequestPart("projectActivityDto") ProjectActivityDto projectActivityDto) throws IOException {
        return new ResponseEntity<>(this.ieDcSvc.insertProjectActivity(file, projectActivityDto), HttpStatus.OK);
    }

    @GetMapping("ProgressFiles")
    public ResponseEntity<?> getProgressFiles(@RequestParam Integer projectId, @RequestParam Integer projectProgressId) {
        return new ResponseEntity<>(this.ieDcSvc.getProgressFiles(projectId,projectProgressId), HttpStatus.OK);
    }

    @PostMapping("UploadProgressFile")
    public ResponseEntity<?> uploadProgressFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("projectId") Integer projectId,
            @RequestParam("projectProgressId") Integer projectProgressId) {
        return new ResponseEntity<>(this.ieDcSvc.uploadProgressFiles(files, projectId, projectProgressId), HttpStatus.OK);
    }


}
