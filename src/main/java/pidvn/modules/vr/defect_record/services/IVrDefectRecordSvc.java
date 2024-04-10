package pidvn.modules.vr.defect_record.services;

import pidvn.entities.one.DefectRecord;
import pidvn.modules.vr.defect_record.models.DefectRecordVo;

import java.util.List;

public interface IVrDefectRecordSvc {
    List<Process> getProcesses(String line);
    List<DefectRecordVo> getDefectRecords(Integer processId, String lotNo);
    List<DefectRecord> saveDefectRecords(List<DefectRecord> defectRecords);

}
