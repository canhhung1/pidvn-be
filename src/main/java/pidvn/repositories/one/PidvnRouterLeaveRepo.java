package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PidvnRouterLeave;

@Repository
public interface PidvnRouterLeaveRepo extends JpaRepository<PidvnRouterLeave, Integer> {
}
