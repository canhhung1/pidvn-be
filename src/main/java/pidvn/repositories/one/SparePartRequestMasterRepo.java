package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartRequestMaster;

@Repository
public interface SparePartRequestMasterRepo extends JpaRepository<SparePartRequestMaster, Integer> {

    @Query(value = "select count(R.id) from spare_part_request_master R where DATE_FORMAT(R.created_at, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')", nativeQuery = true)
    Integer getTotalRequestInMonth();
}
