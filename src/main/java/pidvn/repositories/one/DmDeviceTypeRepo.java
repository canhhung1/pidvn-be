package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.DmDeviceType;

@Repository
public interface DmDeviceTypeRepo extends JpaRepository<DmDeviceType, Integer> {
}
