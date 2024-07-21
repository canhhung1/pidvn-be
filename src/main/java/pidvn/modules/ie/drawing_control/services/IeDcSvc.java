package pidvn.modules.ie.drawing_control.services;

import pidvn.modules.ie.drawing_control.models.*;

import java.util.List;

public interface IeDcSvc {

    List<ProjectDto> getProjects(SearchDto searchDto);
    ProjectDto insertProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto);
    List<ProjectTypeDto> getProjectTypes();
    ProjectDto getProjectById(Integer projectId);
    List<ProjectProgressDto> getProjectProgresses(Integer projectId);

    ProjectProgressDto updateProjectProgress(ProjectProgressDto projectProgressDto);

    List<DrawingDto> getDrawings(Integer projectId);

    DrawingDto saveDrawing(DrawingDto drawingDto);
}
