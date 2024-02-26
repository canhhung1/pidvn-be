package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.QaTrainingMatrixCourse;

@Repository
public interface QaTrainingMatrixCourseRepo extends JpaRepository<QaTrainingMatrixCourse, Integer> {
}
