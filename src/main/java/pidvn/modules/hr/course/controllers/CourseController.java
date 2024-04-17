package pidvn.modules.hr.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.hr.course.models.CourseVo;
import pidvn.modules.hr.course.services.HrCourseSvc;

import java.io.IOException;

@RestController
@RequestMapping("HR/Course")
public class CourseController {

    @Autowired
    private HrCourseSvc hrCourseSvc;

    @PostMapping("Courses")
    public ResponseEntity<?> getCourse(@RequestBody CourseVo searchVo) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.hrCourseSvc.getUsers(), HttpStatus.OK);
    };
    @GetMapping("Course-Groups")
    public ResponseEntity<?> getCourseGroups() {
        return new ResponseEntity<>(this.hrCourseSvc.getCourseGroups(),HttpStatus.OK);
    }

    @PostMapping("CourseHistory")
    public ResponseEntity<?> getTrainingRecords() {
        return new ResponseEntity<>(this.hrCourseSvc.getCourseHistories(), HttpStatus.OK);
    }

    @PostMapping("UploadTrainingRecordData")
    public ResponseEntity<?> uploadTrainingRecordData(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.hrCourseSvc.uploadTrainingRecordData(file), HttpStatus.OK);
    }



}
