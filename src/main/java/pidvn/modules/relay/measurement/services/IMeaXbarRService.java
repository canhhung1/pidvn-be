package pidvn.modules.relay.measurement.services;

import pidvn.modules.relay.measurement.models.xbar.XbarDataVo;
import pidvn.modules.relay.measurement.models.xbar.MeaSearchVo;

import java.util.List;

public interface IMeaXbarRService {
    List<XbarDataVo> getXbarData(MeaSearchVo searchVo);
}
