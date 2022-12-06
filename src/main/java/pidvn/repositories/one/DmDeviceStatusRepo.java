package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.DmDeviceStatus;

@Repository
public interface DmDeviceStatusRepo extends JpaRepository<DmDeviceStatus, Integer> {
}
