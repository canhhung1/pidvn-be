package pidvn.modules.warehouse.iqc_recheck.services;

import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.Lots;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;
import pidvn.modules.warehouse.iqc_recheck.models.LotDto;
import pidvn.modules.warehouse.iqc_recheck.models.RequestDto;

import java.util.List;

public interface IWhIqcRecheckSvc {

    List<Lots> scanLabel(LabelDto labelDto);
    IqcRequest createIqcRecheckRequest(RequestDto requestDto);
    List<LotDto> getLotGroupsIqcOver6Month();
}
