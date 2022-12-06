package pidvn.modules.relay.training_record.services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.relay.training_record.models.*;

import java.io.IOException;
import java.util.List;

public interface ITrainingRecordSvc {
    List<UserVo> getUsers();

    TrainingRecordMaster createTrainingRecordMaster(TrainingRecordMaster master);

    List<TrainingRecordMasterVo> getTrainingRecordMaster(TrainingRecordMasterVo masterVo);

    TrainingRecordDetail saveTrainingRecordDetail(TrainingRecordDetail detail, Integer master, String type, String scoreOfPass);

    List<TrainingRecordDetailVo> getTrainingRecordDetail(TrainingRecordDetailVo searchVo);

    List<TrainingRecordType> getTrainingRecordType();

    TrainingRecordMaster approveTrainingRecord(Integer master, String username);

    List<TrainingRecordDetail> uploadUsers(MultipartFile file, Integer master) throws IOException;

    Users attendanceUser(TrainingRecordDetailVo detailVo) throws Exception;

    List<TRCourseVo> getCourses();

    TrainingRecordCourse saveCourse(TRCourseVo courseVo, String action);

    TrainingRecordMaster saveTrainingRecordMaster(TrainingRecordMasterVo masterVo, String action);

    List<HistoriesVo> getHistories();

}
