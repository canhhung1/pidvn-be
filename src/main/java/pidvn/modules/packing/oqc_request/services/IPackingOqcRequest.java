package pidvn.modules.packing.oqc_request.services;

import pidvn.entities.one.OqcRequest;
import pidvn.modules.packing.oqc_request.models.DataSummaryVo;
import pidvn.modules.packing.oqc_request.models.OqcRequestVo;
import pidvn.modules.packing.oqc_request.models.SearchVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPackingOqcRequest {
    Map createOqcRequest(OqcRequestVo oqcRequestVo);

    OqcRequest updateOqcRequest(OqcRequestVo oqcRequestVo);

    List<DataSummaryVo> summaryData(SearchVo searchVo);
}
