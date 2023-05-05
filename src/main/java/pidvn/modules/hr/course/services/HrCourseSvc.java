package pidvn.modules.hr.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.HrCourseGroup;
import pidvn.entities.one.Users;
import pidvn.mappers.one.hr.course.CourseMapper;
import pidvn.modules.hr.course.models.CourseVo;
import pidvn.repositories.one.HrCourseGroupsRepo;
import pidvn.repositories.one.UsersRepo;

import java.util.List;

@Service
public class HrCourseSvc implements IHrCourseSvc{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private HrCourseGroupsRepo hrCourseGroupsRepo;

    @Override
    public List<CourseVo> getCourse(CourseVo searchVo) {
        List<CourseVo> courses = this.courseMapper.getCourse(searchVo);
        return courses;

    }

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAll();
    }

    @Override
    public List<HrCourseGroup> getCourseGroups() {
        return this.hrCourseGroupsRepo.findAll();
    }
}
