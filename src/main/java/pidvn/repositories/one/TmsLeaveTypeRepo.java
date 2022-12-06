package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmsLeaveTypeRepo extends JpaRepository<pidvn.entities.one.TmsLeaveType, Integer> {
}
