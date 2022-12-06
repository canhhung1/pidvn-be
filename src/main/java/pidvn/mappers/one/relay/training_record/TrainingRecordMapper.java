package pidvn.mappers.one.relay.training_record;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pidvn.modules.relay.training_record.models.*;

import java.util.List;

@Mapper
public interface TrainingRecordMapper {
    List<TrainingRecordMasterVo> getTrainingRecordMaster(TrainingRecordMasterVo masterVo);
    List<TrainingRecordDetailVo> getTrainingRecordDetail(TrainingRecordDetailVo searchVo);
    List<TRCourseVo> getCourses();
    List<UserVo> getUsers();
    List<HistoriesVo> getHistories();
}
