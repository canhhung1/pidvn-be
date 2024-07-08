package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrMealRecord;

@Repository
public interface HrMealRecordRepo extends JpaRepository<HrMealRecord, Integer> {

    @Modifying
    @Query(value = "delete FROM hr_meal_record WHERE ngay_cong BETWEEN DATE_SUB(CURDATE(), INTERVAL (DAY(CURDATE()) - 1) DAY) - INTERVAL 1 month AND CURDATE()", nativeQuery = true)
    void deleteMealRecordsPreviousMonth();
}
