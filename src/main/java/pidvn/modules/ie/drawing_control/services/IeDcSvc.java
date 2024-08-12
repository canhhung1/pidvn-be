package pidvn.modules.ie.drawing_control.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.IeDc009;
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

    Map uploadDrawingFile(MultipartFile[] files, String projectNo);

    Map uploadDrawingTreeList(MultipartFile file, Integer projectId) throws IOException;

    List<ProjectActivityDto> getProjectActivities(Integer projectId);

    ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto) throws IOException;

    List<Product> getProducts();

    Map uploadProgressFiles(MultipartFile[] files, Integer projectId, Integer projectProgressId);

    List<IeDc009> getProgressFiles(Integer projectId, Integer projectProgressId);

    List<ProjectProgressDto> getProgressesByProjectType(Integer projectTypeId);
}
