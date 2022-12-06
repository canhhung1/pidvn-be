package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.TrainingRecordDetail;

import java.util.List;

@Repository
public interface TrainingRecordDetailRepo extends JpaRepository<TrainingRecordDetail, Integer> {
    List<TrainingRecordDetail> findByUsernameAndTrainingRecordMaster(String username, Integer trainingRecordMaster);
}
