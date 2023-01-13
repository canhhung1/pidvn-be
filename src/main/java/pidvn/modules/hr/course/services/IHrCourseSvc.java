package pidvn.modules.hr.course.services;

import pidvn.modules.hr.course.models.CourseVo;

import java.util.List;

public interface IHrCourseSvc {
    List<CourseVo> getCourse(CourseVo searchVo);
}
