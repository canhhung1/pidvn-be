package pidvn.modules.ie.drawing_management.services;

import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;

import java.util.List;

public interface IDrawingMngSvc {

    List<ProjectVo> getProjects(SearchVo searchVo);
}
