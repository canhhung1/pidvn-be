package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrCourseHistory;

@Repository
public interface HrCourseHistoryRepo extends JpaRepository<HrCourseHistory, Integer> {
}
