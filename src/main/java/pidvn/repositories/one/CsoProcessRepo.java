package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.CsoProcess;

@Repository
public interface CsoProcessRepo extends JpaRepository<CsoProcess, Integer> {
}
