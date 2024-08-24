package pidvn.modules.ie.drawing_control.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectTypeDto;
import pidvn.modules.ie.drawing_control.models.UserDto;
import pidvn.modules.ie.drawing_control.services.IeDcSvcImpl;

import java.util.Arrays;
import java.util.List;

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


}
