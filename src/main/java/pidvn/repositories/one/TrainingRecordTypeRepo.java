package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.TrainingRecordType;

@Repository
public interface TrainingRecordTypeRepo extends JpaRepository<TrainingRecordType, Integer> {
}
