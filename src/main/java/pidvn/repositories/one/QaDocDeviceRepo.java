package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.QaDocDevice;

import java.util.List;

@Repository
public interface QaDocDeviceRepo extends JpaRepository<QaDocDevice, Integer> {


}
