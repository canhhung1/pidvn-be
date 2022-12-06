package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Process;

import java.util.List;

@Repository
public interface ProcessRepo extends JpaRepository<Process, Integer> {
    List<Process> findByProductId(Integer productId);
}
