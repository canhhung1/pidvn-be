package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.StopGroups;

@Repository
public interface StopGroupsRepo extends JpaRepository<StopGroups, Integer> {
}
