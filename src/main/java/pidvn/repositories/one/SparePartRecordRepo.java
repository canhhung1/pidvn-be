package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartRecord;

@Repository
public interface SparePartRecordRepo extends JpaRepository<SparePartRecord, Integer> {
}
