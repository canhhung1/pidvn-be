package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.OqcRequest;

import java.util.List;

@Repository
public interface OqcRequestRepo extends JpaRepository<OqcRequest, Integer> {
    List<OqcRequest> findByReqNo(String reqNo);
    List<OqcRequest> findByQaCard(String qaCard);

    @Query(value = "select count(R.id) from oqc_request R where DATE_FORMAT(R.created_at, '%Y-%m-%d') = CURDATE()", nativeQuery = true)
    Integer getTotalRequestInDay();

    @Query(value = "select count(R.id) from oqc_request R where DATE_FORMAT(R.created_at, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')", nativeQuery = true)
    Integer getTotalRequestInMonth();

//    @Query(value = "select * from oqc_request R where DATE_FORMAT(R.created_at, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')")
//    List<OqcRequest> getAllByCurrentMonth();
}
