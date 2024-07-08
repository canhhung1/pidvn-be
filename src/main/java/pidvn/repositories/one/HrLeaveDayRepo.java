package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrLeaveDay;

@Repository
public interface HrLeaveDayRepo extends JpaRepository<HrLeaveDay, Integer> {

    @Modifying
    @Query(value = "delete FROM hr_leaveday WHERE date_start BETWEEN DATE_SUB(CURDATE(), INTERVAL (DAY(CURDATE()) - 1) DAY) - INTERVAL 1 month AND CURDATE()", nativeQuery = true)
    void deleteLeaveDayPreviousMonth();
}
