package pidvn.mappers.one.ie.drawing_control;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.ie.drawing_control.models.ProcessDto;
import pidvn.modules.ie.drawing_control.models.ProjectDto;

import java.util.List;

@Mapper
public interface IeDcMapper {
    List<ProjectDto> getProjects();
    List<ProcessDto> getProcesses(Integer projectId);
}
