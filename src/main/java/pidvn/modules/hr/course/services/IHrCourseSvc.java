package pidvn.modules.hr.course.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.HrCourseGroup;
import pidvn.entities.one.Users;
import pidvn.modules.hr.course.models.CourseVo;
import pidvn.modules.hr.course.models.CourseHistoryVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHrCourseSvc {
    List<CourseVo> getCourse(CourseVo searchVo);
    List<Users> getUsers();
    List<HrCourseGroup> getCourseGroups();
    List<CourseHistoryVo> getCourseHistories();
    Map uploadTrainingRecordData(MultipartFile file) throws IOException;
}
