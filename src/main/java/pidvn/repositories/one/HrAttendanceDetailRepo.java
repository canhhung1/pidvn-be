package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrAttendanceDetail;

@Repository
public interface HrAttendanceDetailRepo extends JpaRepository<HrAttendanceDetail, Integer> {

    @Modifying
    @Query(value = "delete from hr_attendance_detail where DATE_FORMAT(ngay_cong, '%Y-%m') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m')", nativeQuery = true)
    void deleteAttendanceDetailsPreviousMonth();



}
