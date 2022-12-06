package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.StopTypes;

@Repository
public interface StopTypesRepo extends JpaRepository<StopTypes, Integer> {
}
