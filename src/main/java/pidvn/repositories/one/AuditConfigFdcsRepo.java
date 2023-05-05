package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.AuditConfigFdcs;

import java.util.List;

@Repository
public interface AuditConfigFdcsRepo extends JpaRepository<AuditConfigFdcs, Integer> {

    List<AuditConfigFdcs> findByConfigName(String configName);
}
