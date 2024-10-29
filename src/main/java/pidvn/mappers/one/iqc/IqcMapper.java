package pidvn.mappers.one.iqc;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.iqc.models.*;

import java.util.List;

@Mapper
public interface IqcMapper {

    List<IqcRequestDto> getIqcRequests(SearchDto searchDto);
    IqcRequestDto getIqcRequest(String requestNo);
    List<PurWhRecordDto> getSlipNo();
    List<PurWhRecordDto> getPurWhRecords(IqcRequestDto iqcRequestDto);
    List<IqcResultDto> getIqcResults(String requestNo);
    List<PihStoreDto> getLotsInventory(String type);
    List<PihStoreDto> prepareDataCreateRequest(SearchDto searchDto);
    List<IqcResultDto> getPihStore(List<String> lotNos);



}
