package pidvn.mappers.one.vr.defect_record;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.vr.defect_record.models.DefectRecordVo;

import java.util.List;

@Mapper
public interface VrDefectRecordMapper {
    List<Process> getProcesses(String line);
    List<DefectRecordVo> getDefectRecords(Integer processId, String lotNo);
    List<DefectRecordVo> summaryDefects(String lotNo);
}
