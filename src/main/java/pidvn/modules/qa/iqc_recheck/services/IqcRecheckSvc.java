package pidvn.modules.qa.iqc_recheck.services;


import pidvn.modules.qa.iqc_recheck.models.LotDto;

import java.util.List;
import java.util.Map;


public interface IqcRecheckSvc {


    List<LotDto> getLotsInRequest(String requestNo, String goodsType);

    Map<String, Object> saveIqcResult(List<LotDto> lots, String goodsType);
}
