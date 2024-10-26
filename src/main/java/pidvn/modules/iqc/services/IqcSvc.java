package pidvn.modules.iqc.services;

import pidvn.entities.one.IqcLevelOfControl;
import pidvn.modules.iqc.models.IqcRequestDto;
import pidvn.modules.iqc.models.IqcResultDto;
import pidvn.modules.iqc.models.PurWhRecordDto;
import pidvn.modules.iqc.models.SearchDto;

import java.util.List;
import java.util.Map;

public interface IqcSvc {
    List<IqcRequestDto> getIqcRequests(SearchDto searchDto);
    IqcRequestDto getIqcRequest(String requestNo);
    List<PurWhRecordDto> getSlipNo();
    Map<Object, Object> createIqcRequest(IqcRequestDto iqcRequestDto);
    IqcRequestDto updateIqcRequest(IqcRequestDto iqcRequestDto);


    List<IqcResultDto> getIqcResults(String requestNo);
    List<IqcLevelOfControl> getIqcLevelOfControls();
    List<IqcResultDto> evaluateLotNos(List<IqcResultDto> iqcResults);
}
