package pidvn.modules.ie.drawing_management.services;

import pidvn.entities.one.IeDrawing;
import pidvn.entities.one.IeProject;
import pidvn.entities.one.IeProjectProgress;
import pidvn.entities.one.IeProjectType;
import pidvn.modules.ie.drawing_management.models.ProgressVo;
import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;

import java.util.List;

public interface IDrawingMngSvc {

    IeProject getIeProjectById(Integer id);

    List<ProjectVo> getProjects(SearchVo searchVo);

    IeProject saveIeProject(IeProject ieProject);

    List<IeProjectProgress> getProjectProcesses(Integer projectId);

    List<IeDrawing> getIeDrawings(Integer projectId, Integer progressId);

    IeDrawing saveIeDrawing(IeDrawing ieDrawing);

    List<IeProjectType> getIeProjectTypes();

    List<ProgressVo> getProgressByProject(SearchVo searchVo);

}
