package pidvn.modules.administrator.services;

import pidvn.entities.one.Section;
import pidvn.modules.administrator.models.MenuReportVo;

import java.util.List;

public interface IMenuReportService {
    List<MenuReportVo> getMenuReport(String dept);

    List<Section> getSections();
}
