package pidvn.modules.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.dashboard.DashboardMapper;
import pidvn.modules.dashboard.models.RelayQtyVo;

import java.util.List;

@Service
public class DashboardSvc implements IDashboardSvc {

    @Autowired
    private DashboardMapper dashboardMapper;


    @Override
    public List<RelayQtyVo> getManufacturingQuantity() {
        return dashboardMapper.getManufacturingQuantity();
    }
}
