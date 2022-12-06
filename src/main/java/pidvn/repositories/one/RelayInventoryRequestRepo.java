package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.RelayInventoryRequest;

import java.util.Date;
import java.util.List;

@Repository
public interface RelayInventoryRequestRepo extends JpaRepository<RelayInventoryRequest, Integer> {
    List<RelayInventoryRequest> findByInventoryDate(Date date);
    RelayInventoryRequest findByReqNo(String reqNo);
}
