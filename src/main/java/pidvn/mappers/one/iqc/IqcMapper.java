package pidvn.mappers.one.iqc;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.iqc.models.IqcRequestDto;
import pidvn.modules.iqc.models.IqcResultDto;
import pidvn.modules.iqc.models.PurWhRecordDto;
import pidvn.modules.iqc.models.SearchDto;

import java.util.List;

@Mapper
public interface IqcMapper {

    List<IqcRequestDto> getIqcRequests(SearchDto searchDto);
    IqcRequestDto getIqcRequest(String requestNo);
    List<PurWhRecordDto> getSlipNo();
    List<PurWhRecordDto> getPurWhRecords(IqcRequestDto iqcRequestDto);
    List<IqcResultDto> getIqcResults(String requestNo);
}
