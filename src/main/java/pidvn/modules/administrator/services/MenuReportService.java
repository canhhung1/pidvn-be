package pidvn.modules.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.administrator.MenuReportMapper;
import pidvn.modules.administrator.models.MenuReportVo;

import java.util.List;

@Service
public class MenuReportService implements IMenuReportService {

    @Autowired
    private MenuReportMapper MenuReportMapper;

    @Override
    public List<MenuReportVo> getMenuReport() {
        return this.MenuReportMapper.getMenuReport();
    }
}
