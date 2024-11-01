package pidvn.modules.iqc.services;

import pidvn.entities.one.IqcLevelOfControl;
import pidvn.modules.iqc.models.*;

import java.util.List;
import java.util.Map;

public interface IqcSvc {
    List<IqcRequestDto> getIqcRequests(SearchDto searchDto);
    IqcRequestDto getIqcRequest(String requestNo);
    List<PurWhRecordDto> getSlipNo();
    Map<Object, Object> createIqcRequest(IqcRequestDto iqcRequestDto) throws Exception;
    IqcRequestDto updateIqcRequest(IqcRequestDto iqcRequestDto);


    List<IqcResultDto> getIqcResults(String requestNo);
    List<IqcLevelOfControl> getIqcLevelOfControls();
    List<IqcResultDto> evaluateLotNos(List<IqcResultDto> iqcResults);
    Map<String, Object> getLotsInventory();

    List<PihStoreDto> prepareDataCreateRequest(SearchDto searchDto);
    List<IqcResultDto> getHistoryLevelOfControls(String model);
    List<IqcResultDto> getIqcResultsExportExcel(String iqcRequest);
}
