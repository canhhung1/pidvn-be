package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.QaDevice;

import java.util.Optional;

@Repository
public interface QaDeviceRepo extends JpaRepository<QaDevice, Integer> {

    Optional<QaDevice> findByDeviceNo(String deviceNo);
}
