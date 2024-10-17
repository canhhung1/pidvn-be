package pidvn.modules.ie.drawing_control.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.commons.dto.ApiResponse;
import pidvn.entities.one.IeDc001;
import pidvn.entities.one.Product;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.modules.ie.drawing_control.services.IeDcSvcImpl;
import reactor.util.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("IE/DrawingControl")
public class IeDcCtl {

    @Autowired
    private IeDcSvcImpl ieDcSvc;

    @GetMapping("Products")
    private ResponseEntity<ApiResponse<?>> getProducts() {
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProducts());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("Users")
    private ResponseEntity<ApiResponse<?>> getUsers() {
        List<Integer> userIds = Arrays.asList(13, 32);
        ApiResponse<List<UserDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getUsers(userIds));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Lấy danh sách Project
     * @return
     */
    @PostMapping("Projects")
    public ResponseEntity<ApiResponse<?>> getProjects() {
        ApiResponse<List<ProjectDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProjects());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    /**
     * Lấy thông tin project theo Id
     * @param id
     * @return
     */
    @GetMapping("Project/{id}")
    public ResponseEntity<ApiResponse<?>> getProject(@PathVariable Integer id) {
        ApiResponse<ProjectDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProject(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Tạo mới Project
     * @param projectDto
     * @return
     */
    @PostMapping("Project")
    public ResponseEntity<ApiResponse<?>> createProject(@RequestBody ProjectDto projectDto) {
        ApiResponse<ProjectDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.createProject(projectDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("Project")
    public ResponseEntity<ApiResponse<?>> updateProject(@RequestBody ProjectDto projectDto) {
        ApiResponse<ProjectDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.updateProject(projectDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("Project/{projectId}")
    public ResponseEntity<ApiResponse<?>> deleteProject(@PathVariable Integer projectId) {
        ApiResponse<ProjectDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.deleteProject(projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Lấy ra các loại project (Project Type)
     * @return
     */
    @GetMapping("ProjectTypes")
    public ResponseEntity<ApiResponse<?>> getProjectTypes() {
        ApiResponse<List<ProjectTypeDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProjectTypes());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Lấy project theo projectId
     * @param projectId
     * @return
     */
    @GetMapping("Processes")
    public ResponseEntity<?> getProcesses(@RequestParam @Nullable Integer projectId) {
        ApiResponse<List<ProcessDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProcesses(projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("UploadDrawingStructure")
    public ResponseEntity<?> uploadDrawingStructure(@RequestBody MultipartFile file, @RequestParam Integer projectId) throws IOException {
        ApiResponse<List<DrawingDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.uploadDrawingStructure(file, projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("DrawingStructure")
    public ResponseEntity<?> getDrawingStructure(@RequestParam Integer projectId) {
        ApiResponse<List<DrawingDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getDrawingStructure(projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("DrawingFiles")
    public ResponseEntity<ApiResponse<?>> uploadDrawingFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam Integer projectId) {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.uploadDrawingFiles(files, projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Preview nếu là file pdf
     * @param drawingDto
     * @return
     */
    @PostMapping(value = "DrawingPreview")
    public ResponseEntity<byte[]> previewDrawingFile(
            @RequestBody DrawingDto drawingDto, @RequestParam String controlNo) throws IOException {

        String ROOT_FOLDER = this.ieDcSvc.ROOT_FOLDER;
        String path = ROOT_FOLDER + controlNo + "\\Drawing\\" + drawingDto.getDrawingNo() + ".pdf";

        File file = new File(path);
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

    @PostMapping("ProjectActivity")
    public ResponseEntity<?> insertProjectActivity(
            @RequestPart("file") @Nullable MultipartFile file,
            @RequestPart("projectActivity") ProjectActivityDto projectActivityDto) throws IOException {
        ApiResponse<ProjectActivityDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.insertProjectActivity(file, projectActivityDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("ProjectActivities")
    public ResponseEntity<?> getProjectActivities(@RequestParam Integer projectId) {
        ApiResponse<List<ProjectActivityDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProjectActivities(projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("ProcessRecord")
    public ResponseEntity<?> getProcessRecordByProject(@RequestParam Integer projectId) {
        ApiResponse<List<ProcessRecordDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.getProcessRecordByProject(projectId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("ProcessRecord")
    public ResponseEntity<?> saveProcessRecord(@RequestBody ProcessRecordDto processRecordDto) {
        ApiResponse<ProcessRecordDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.ieDcSvc.saveProcessRecord(processRecordDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
