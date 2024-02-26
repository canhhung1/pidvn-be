package pidvn.modules.qa.qa_training_matrix.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.QaTrainingMatrixCourse;
import pidvn.modules.qa.qa_training_matrix.models.CourseVo;
import pidvn.modules.qa.qa_training_matrix.models.TrainingRecordVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IQaTrainingMatrixSvc {

    Map insertByExcel (MultipartFile file);
    List<CourseVo> getCourses();

    List<TrainingRecordVo> getTrainingRecords(Date trainingDate, Integer courseId);


}
