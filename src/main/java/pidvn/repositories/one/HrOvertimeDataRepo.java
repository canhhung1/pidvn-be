package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrOvertimeData;

import java.util.List;

@Repository
public interface HrOvertimeDataRepo extends JpaRepository<HrOvertimeData, Integer> {

    @Modifying
    @Query(value = "delete from hr_overtime_data where DATE_FORMAT(ngay_cong, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m')", nativeQuery = true)
    void deleteOvertimeDataPreviousMonth();
}
