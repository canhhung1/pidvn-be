package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrAttendanceDetail;

@Repository
public interface HrAttendanceDetailRepo extends JpaRepository<HrAttendanceDetail, Integer> {

    @Modifying
    @Query(value = "delete from hr_attendance_detail where ngay_cong >= DATE_ADD(CURDATE(), INTERVAL -1 MONTH)  AND ngay_cong <= CURDATE()", nativeQuery = true)
    void deleteAttendanceDetailsPreviousMonth();



}
