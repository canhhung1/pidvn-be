package pidvn.modules.dashboard.services;

import pidvn.modules.dashboard.models.RelayQtyVo;

import java.util.List;

public interface IDashboardSvc {

    List<RelayQtyVo> getManufacturingQuantity();
}
