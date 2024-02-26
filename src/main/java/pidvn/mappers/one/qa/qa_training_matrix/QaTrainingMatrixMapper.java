package pidvn.mappers.one.qa.qa_training_matrix;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.qa.qa_training_matrix.models.CourseVo;
import pidvn.modules.qa.qa_training_matrix.models.TrainingRecordVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface QaTrainingMatrixMapper {

    List<CourseVo> getCourses();
    List<TrainingRecordVo> getTrainingRecords(Date trainingDate, Integer courseId);
}
