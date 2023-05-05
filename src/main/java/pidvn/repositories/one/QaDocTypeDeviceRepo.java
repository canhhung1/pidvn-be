package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.QaDocTypeDevice;

@Repository
public interface QaDocTypeDeviceRepo extends JpaRepository<QaDocTypeDevice, Integer> {
}
