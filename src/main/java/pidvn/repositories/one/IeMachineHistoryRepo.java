package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeMachineHistory;

@Repository
public interface IeMachineHistoryRepo extends JpaRepository<IeMachineHistory, Integer> {
}
