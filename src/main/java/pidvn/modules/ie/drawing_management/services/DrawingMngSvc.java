package pidvn.modules.ie.drawing_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.ie.drawing_management.DrawingMngMapper;
import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;

import java.util.List;

@Service
public class DrawingMngSvc implements IDrawingMngSvc {

    @Autowired
    private DrawingMngMapper drawingMngMapper;

    @Override
    public List<ProjectVo> getProjects(SearchVo searchVo) {
        return this.drawingMngMapper.getProjects(searchVo);
    }
}
