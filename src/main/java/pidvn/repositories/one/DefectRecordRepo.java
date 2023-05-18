package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.DefectRecord;

@Repository
public interface DefectRecordRepo extends JpaRepository<DefectRecord, Integer> {

}
