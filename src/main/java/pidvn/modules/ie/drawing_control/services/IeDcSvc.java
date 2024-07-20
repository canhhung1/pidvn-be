package pidvn.modules.ie.drawing_control.services;

import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectProgressDto;
import pidvn.modules.ie.drawing_control.models.ProjectTypeDto;
import pidvn.modules.ie.drawing_control.models.SearchDto;

import java.util.List;

public interface IeDcSvc {

    List<ProjectDto> getProjects(SearchDto searchDto);
    ProjectDto insertProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto);
    List<ProjectTypeDto> getProjectTypes();
    ProjectDto getProjectById(Integer projectId);
    List<ProjectProgressDto> getProjectProgresses(Integer projectId);
}
