package pidvn.modules.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Section;
import pidvn.mappers.one.administrator.MenuReportMapper;
import pidvn.modules.administrator.models.MenuReportVo;
import pidvn.repositories.one.SectionRepo;

import java.util.List;

@Service
public class MenuReportService implements IMenuReportService {

    @Autowired
    private MenuReportMapper MenuReportMapper;

    @Autowired
    private SectionRepo sectionRepo;

    @Override
    public List<MenuReportVo> getMenuReport(String dept) {
        return this.MenuReportMapper.getMenuReport(dept);
    }

    @Override
    public List<Section> getSections() {
        return this.sectionRepo.findAll();
    }
}
