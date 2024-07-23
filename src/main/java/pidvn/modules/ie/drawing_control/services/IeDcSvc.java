package pidvn.modules.ie.drawing_control.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.ie.drawing_control.models.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    Map uploadDrawing(MultipartFile file, String projectNo, String drawingName);

    Map uploadDrawingTreeList(MultipartFile file, Integer projectId) throws IOException;

    List<ProjectActivityDto> getProjectActivities(Integer projectId);

    ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto);
}
