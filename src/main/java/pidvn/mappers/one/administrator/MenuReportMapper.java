package pidvn.mappers.one.administrator;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.administrator.models.MenuReportVo;

import java.util.List;

@Mapper
public interface MenuReportMapper {
    List<MenuReportVo> getMenuReport();
}
