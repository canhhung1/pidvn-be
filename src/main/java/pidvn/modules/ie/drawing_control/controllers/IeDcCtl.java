package pidvn.modules.ie.drawing_control.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.commons.dto.ApiResponse;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.modules.ie.drawing_control.services.IeDcSvcImpl;
import reactor.util.annotation.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("IE/DrawingControl")
public class IeDcCtl {

    @Autowired
    private IeDcSvcImpl ieDcSvc;

    @GetMapping("Users")
    private ResponseEntity<?> getUsers() {
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

}
