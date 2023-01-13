package pidvn.modules.hr.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
