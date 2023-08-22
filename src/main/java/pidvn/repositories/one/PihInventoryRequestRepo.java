package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihInventoryRequest;

import java.util.List;

@Repository
public interface PihInventoryRequestRepo extends JpaRepository<PihInventoryRequest, Integer> {

    List<PihInventoryRequest> findAllByOrderByIdDesc();
    PihInventoryRequest findByReqNo(String reqNo);
}
