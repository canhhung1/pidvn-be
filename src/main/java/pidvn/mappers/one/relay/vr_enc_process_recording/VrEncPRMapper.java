package pidvn.mappers.one.relay.vr_enc_process_recording;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.vr_enc_process_recording.models.*;

import java.util.List;

@Mapper
public interface VrEncPRMapper {
    List<MaterialVo> getMaterialHistories(String lotNo);
    List<MaterialVo> getMaterials(SearchVo searchVo);
    List<PartVo> getPartsByModel(String model);
    List<ProcessVo> getProcesses(String productTypeName);
    List<ProcessVo> getProcessesVer2(String line);
    List<QaCardVo> getQaCards(SearchVo searchVo);
    List<MaterialVo> traceability(SearchVo searchVo);
    List<ModelVo> getModels();

    List<DefectRecordVo> getDefectRecord(String lotNo);



}
