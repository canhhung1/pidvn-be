package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihInventoryRequest;

import java.util.List;

@Repository
public interface PihInventoryRequestRepo extends JpaRepository<PihInventoryRequest, Integer> {

    List<PihInventoryRequest> findAllByOrderByIdDesc();

    PihInventoryRequest findByReqNo(String reqNo);

    @Query(value = "SELECT * FROM pih_inventory_request where DATE_FORMAT(created_at, \"%Y-%m\") = DATE_FORMAT(SYSDATE(), \"%Y-%m\");", nativeQuery = true)
    List<PihInventoryRequest> findByCurrentMonth();
}
