package pidvn.modules.ie.drawing_control.services;

import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectTypeDto;
import pidvn.modules.ie.drawing_control.models.UserDto;

import java.util.List;

public interface IeDcSvc {
    List<UserDto> getUsers(List<Integer> subsectionIds);
    ProjectDto createProject(ProjectDto projectDto);
    List<ProjectDto> getProjects();
    List<ProjectTypeDto> getProjectTypes();
}
