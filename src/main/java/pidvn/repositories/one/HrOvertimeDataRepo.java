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
    @Query(value = "delete FROM hr_overtime_data WHERE ngay_cong BETWEEN DATE_SUB(CURDATE(), INTERVAL (DAY(CURDATE()) - 1) DAY) - INTERVAL 1 month AND CURDATE()", nativeQuery = true)
    void deleteOvertimeDataPreviousMonth();
}
