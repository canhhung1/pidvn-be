package pidvn.modules.packing.oqc_request.services;

import pidvn.modules.packing.oqc_request.models.OqcRequestVo;

import java.util.Map;

public interface IPackingOqcRequest {
    Map createOqcRequest(OqcRequestVo oqcRequestVo);
}
