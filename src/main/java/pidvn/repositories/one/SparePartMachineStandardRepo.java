package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartMachineStandard;

@Repository
public interface SparePartMachineStandardRepo extends JpaRepository<SparePartMachineStandard, Integer> {
}
