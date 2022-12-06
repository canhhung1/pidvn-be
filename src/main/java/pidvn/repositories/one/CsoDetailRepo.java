package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.CsoDetail;

@Repository
public interface CsoDetailRepo extends JpaRepository<CsoDetail, Integer> {
}
