package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Shifts;

@Repository
public interface ShiftsRepo extends JpaRepository<Shifts, Integer> {
}
