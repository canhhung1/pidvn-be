package pidvn.modules.hr.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.hr.course.models.CourseVo;
import pidvn.modules.hr.course.services.HrCourseSvc;

@RestController
@RequestMapping("HR/Course")
public class CourseController {

    @Autowired
    private HrCourseSvc hrCourseSvc;

    @PostMapping("Courses")
    public ResponseEntity<?> getCourse(@RequestBody CourseVo searchVo) {
        return new ResponseEntity<>(this.hrCourseSvc.getCourse(searchVo), HttpStatus.OK);
    }

    @PostMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.hrCourseSvc.getUsers(), HttpStatus.OK);
    };
    @GetMapping("Course-Groups")
    public ResponseEntity<?> getCourseGroups() {
        return new ResponseEntity<>(this.hrCourseSvc.getCourseGroups(),HttpStatus.OK);
    }


}
