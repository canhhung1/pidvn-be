package pidvn.modules.hr.course.services;

import pidvn.entities.one.HrCourseGroup;
import pidvn.entities.one.Users;
import pidvn.modules.hr.course.models.CourseVo;

import java.util.List;

public interface IHrCourseSvc {
    List<CourseVo> getCourse(CourseVo searchVo);
    List<Users> getUsers();
    List<HrCourseGroup> getCourseGroups();

}
