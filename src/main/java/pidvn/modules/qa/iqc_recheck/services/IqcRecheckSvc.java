package pidvn.modules.qa.iqc_recheck.services;


import pidvn.modules.qa.iqc_recheck.models.LotDto;

import java.util.List;


public interface IqcRecheckSvc {


    List<LotDto> getLotsInRequest(String requestNo, String goodsType);
}
