package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrLeaveDay;

@Repository
public interface HrLeaveDayRepo extends JpaRepository<HrLeaveDay, Integer> {

    @Modifying
    @Query(value = "delete from hr_leaveday where date_start >= DATE_ADD(CURDATE(), INTERVAL -1 MONTH)  AND date_start <= CURDATE()", nativeQuery = true)
    void deleteLeaveDayPreviousMonth();
}
