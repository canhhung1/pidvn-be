package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.StopTimes;

@Repository
public interface StopTimesRepo extends JpaRepository<StopTimes, Integer> {
}
