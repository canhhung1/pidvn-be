package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.DmHistory;

@Repository
public interface DmHistoryRepo extends JpaRepository<DmHistory, Integer> {
}
