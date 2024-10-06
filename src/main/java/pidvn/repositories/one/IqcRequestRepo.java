package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcRequest;

@Repository
public interface IqcRequestRepo extends JpaRepository<IqcRequest, Integer> {
    IqcRequest findByRequestNo(String requestNo);

    /**
     * Lấy số lượng request type = 'sorting' trong ngày
     * @return
     */
    @Query(value = "select count(R.id) from iqc_request R where R.type = 'sorting' and DATE_FORMAT(R.created_at, '%Y-%m-%d') = CURDATE()", nativeQuery = true)
    Integer getTotalRequestSortingInDay();


    /**
     * Lấy số lượng request trong 1 ngày đối với các request hàng quá 6 tháng
     * @return
     */
    @Query(value = "select count(R.id) from iqc_request R where R.type = '6month' and DATE_FORMAT(R.created_at, '%Y-%m-%d') = CURDATE()", nativeQuery = true)
    Integer getTotalRequestRecheckInDay();
}
