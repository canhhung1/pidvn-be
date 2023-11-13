package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihInventoryRequest;

import java.util.Date;
import java.util.List;

@Repository
public interface PihInventoryRequestRepo extends JpaRepository<PihInventoryRequest, Integer> {

    List<PihInventoryRequest> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM pih_inventory_request where id <= :id order by id desc", nativeQuery = true)
    List<PihInventoryRequest> findAllByOrderByIdDesc(Integer id);

    PihInventoryRequest findByReqNo(String reqNo);


    /**
     * Tìm các request tính từ ngày :date, trong vòng :month tháng (số tháng)
     * @param date
     * @param month
     * @return
     */
    @Query(value = "select * from pih_inventory_request A \n" +
            "where date_format(A.created_at, '%Y-%m') = date_format(DATE_SUB(:date, INTERVAL :month MONTH), '%Y-%m') \n" +
            "order by A.id desc", nativeQuery = true)
    List<PihInventoryRequest> findDataBeforeMonth(Date date, int month);
}
