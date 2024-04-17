package pidvn.mappers.one.hr.course;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.course.models.CourseHistoryVo;
import pidvn.modules.hr.course.models.CourseVo;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseVo> getCourse(CourseVo searchVo);
    List<CourseHistoryVo> getCourseHistories();
}
