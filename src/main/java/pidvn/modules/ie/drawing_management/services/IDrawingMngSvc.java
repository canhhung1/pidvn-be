package pidvn.modules.ie.drawing_management.services;

import pidvn.entities.one.IeDrawing;
import pidvn.entities.one.IeProjectProgress;
import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;

import java.util.List;

public interface IDrawingMngSvc {

    List<ProjectVo> getProjects(SearchVo searchVo);

    List<IeProjectProgress> getProjectProcesses(Integer projectId);

    List<IeDrawing> getIeDrawings(Integer projectId, Integer progressId);

    IeDrawing saveIeDrawing(IeDrawing ieDrawing);

}
