package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.OqcRequestStatus;

@Repository
public interface OqcRequestStatusRepo extends JpaRepository<OqcRequestStatus, Integer> {
}
