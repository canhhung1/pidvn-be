package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaReason;

@Repository
public interface MeaReasonRepo extends JpaRepository<MeaReason, Integer> {
}
