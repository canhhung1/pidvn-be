package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.TrainingRecordMaster;

@Repository
public interface TrainingRecordMasterRepo extends JpaRepository<TrainingRecordMaster, Integer> {
}
