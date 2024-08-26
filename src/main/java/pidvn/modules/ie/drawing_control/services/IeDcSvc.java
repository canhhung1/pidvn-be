package pidvn.modules.ie.drawing_control.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.ie.drawing_control.models.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IeDcSvc {
    List<UserDto> getUsers(List<Integer> subsectionIds);
    ProjectDto createProject(ProjectDto projectDto);
    List<ProjectDto> getProjects();
    ProjectDto getProject(Integer id);
    List<ProjectTypeDto> getProjectTypes();
    List<ProcessDto> getProcesses(Integer projectId);
    List<DrawingDto> uploadDrawingStructure(MultipartFile file, Integer projectId) throws IOException;

    List<DrawingDto> getDrawingStructure(Integer projectId);

    Map<String, Object> uploadDrawingFiles(MultipartFile[] files, Integer projectId);
}
