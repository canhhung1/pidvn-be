package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.CsoModel;

import java.util.List;

@Repository
public interface CsoModelRepo extends JpaRepository<CsoModel, Integer> {
    List<CsoModel> findAllByOrderByNameAsc();
}
