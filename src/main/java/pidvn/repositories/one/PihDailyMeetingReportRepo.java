package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihDailyMeetingReport;

@Repository
public interface PihDailyMeetingReportRepo extends JpaRepository<PihDailyMeetingReport, Integer> {
}
