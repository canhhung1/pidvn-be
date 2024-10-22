package pidvn.modules.iqc.services;

import pidvn.modules.iqc.models.IqcRequestDto;
import pidvn.modules.iqc.models.PurWhRecordDto;
import pidvn.modules.iqc.models.SearchDto;

import java.util.List;

public interface IqcSvc {
    List<IqcRequestDto> getIqcRequests(SearchDto searchDto);
    List<PurWhRecordDto> getSlipNo();
}
