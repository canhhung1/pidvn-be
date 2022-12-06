package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Position;

@Repository
public interface PositionRepo extends JpaRepository<Position, Integer> {
}
