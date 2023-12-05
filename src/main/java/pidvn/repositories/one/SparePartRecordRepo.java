package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartRecord;

import java.util.List;

@Repository
public interface SparePartRecordRepo extends JpaRepository<SparePartRecord, Integer> {

    @Query(value = "SELECT * FROM spare_part_records WHERE DATE_FORMAT(created_at,'%Y-%m-%d') = CURDATE()", nativeQuery = true)
    List<SparePartRecord> findByCurrentDate();

}
