package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartLineStandard;

@Repository
public interface SparePartLineStandardRepo extends JpaRepository<SparePartLineStandard, Integer> {
}
