package pidvn.modules.hr.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.hr.course.CourseMapper;
import pidvn.modules.hr.course.models.CourseVo;

import java.util.List;

@Service
public class HrCourseSvc implements IHrCourseSvc{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseVo> getCourse(CourseVo searchVo) {
        List<CourseVo> courses = this.courseMapper.getCourse(searchVo);
        return courses;

    }
}
