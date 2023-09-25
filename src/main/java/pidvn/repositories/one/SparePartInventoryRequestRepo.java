package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.entities.one.SparePartInventoryRequest;

import java.util.List;

@Repository
public interface SparePartInventoryRequestRepo extends JpaRepository<SparePartInventoryRequest, Integer> {
    List<SparePartInventoryRequest> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM spare_part_inventory_request where DATE_FORMAT(created_at, \"%Y-%m\") = DATE_FORMAT(SYSDATE(), \"%Y-%m\");", nativeQuery = true)
    List<SparePartInventoryRequest> findByCurrentMonth();
}
