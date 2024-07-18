package pidvn.mappers.one.ie.drawing_management;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.ie.drawing_management.models.ProgressVo;
import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;

import java.util.List;

@Mapper
public interface DrawingMngMapper {

    List<ProjectVo> getProjects(SearchVo search);

    List<ProgressVo> getProgressByProject(SearchVo searchVo);
}
