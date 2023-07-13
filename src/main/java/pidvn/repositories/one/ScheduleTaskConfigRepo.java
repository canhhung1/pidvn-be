package pidvn.repositories.one;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.ScheduleTaskConfig;

@Repository
public interface ScheduleTaskConfigRepo extends JpaRepository<ScheduleTaskConfig, Integer> {
}
