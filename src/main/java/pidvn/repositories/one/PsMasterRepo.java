package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PsMaster;

import java.util.List;

@Repository
public interface PsMasterRepo extends JpaRepository<PsMaster, Integer> {
    List<PsMaster> findByPnpa(String pnpa);
}
