package pidvn.mappers.one.ie.drawing_control;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.ie.drawing_control.models.DrawingDto;
import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectProgressDto;
import pidvn.modules.ie.drawing_control.models.SearchDto;

import java.util.List;

@Mapper
public interface IeDcMapper {
    List<ProjectDto> getProjects(SearchDto searchDto);
    ProjectDto getProjectById(Integer projectId);
    List<ProjectProgressDto> getProjectProgresses(Integer projectId);

    List<DrawingDto> getDrawings(Integer projectId);


    List<ProjectProgressDto> getProgressesByProjectType(Integer projectTypeId);
}
