package pidvn.modules.ie.drawing_control.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.Product;
import pidvn.entities.one.Users;
import pidvn.modules.ie.drawing_control.models.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IeDcSvc {
    List<Users> getPersonInCharges(List<Integer> subsectionIds);
    List<ProjectDto> getProjects(SearchDto searchDto);
    ProjectDto insertProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto);
    List<ProjectTypeDto> getProjectTypes();
    ProjectDto getProjectById(Integer projectId);
    List<ProjectProgressDto> getProjectProgresses(Integer projectId);

    ProjectProgressDto updateProjectProgress(ProjectProgressDto projectProgressDto);

    List<DrawingDto> getDrawings(Integer projectId);

    DrawingDto saveDrawing(DrawingDto drawingDto);

    Map uploadDrawingFile(MultipartFile file, String projectNo);

    Map uploadDrawingTreeList(MultipartFile file, Integer projectId) throws IOException;

    List<ProjectActivityDto> getProjectActivities(Integer projectId);

    ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto);

    List<Product> getProducts();
}
