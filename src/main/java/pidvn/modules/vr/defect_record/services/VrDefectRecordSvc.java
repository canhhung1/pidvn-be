package pidvn.modules.vr.defect_record.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.DefectRecord;
import pidvn.mappers.one.vr.defect_record.VrDefectRecordMapper;
import pidvn.modules.vr.defect_record.models.DefectRecordVo;
import pidvn.repositories.one.DefectRecordRepo;

import java.util.List;

@Service
public class VrDefectRecordSvc implements IVrDefectRecordSvc {

    @Autowired
    private VrDefectRecordMapper vrDefectRecordMapper;

    @Autowired
    private DefectRecordRepo defectRecordRepo;

    /**
     * Lấy các công đoạn trong dây truyền sản xuất
     * @param line tên Line
     * @return
     */
    @Override
    public List<Process> getProcesses(String line) {
        return this.vrDefectRecordMapper.getProcesses(line);
    }

    @Override
    public List<DefectRecordVo> getDefectRecords(Integer processId, String lotNo) {
        return this.vrDefectRecordMapper.getDefectRecords(processId, lotNo);
    }

    @Override
    public List<DefectRecord> saveDefectRecords(List<DefectRecord> defectRecords) {
        return this.defectRecordRepo.saveAll(defectRecords);
    }
}
