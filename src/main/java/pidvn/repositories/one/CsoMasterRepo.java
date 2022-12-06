package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.CsoMaster;

@Repository
public interface CsoMasterRepo extends JpaRepository<CsoMaster, Integer> {
    CsoMaster findByQaCard(String qaCard);
}
