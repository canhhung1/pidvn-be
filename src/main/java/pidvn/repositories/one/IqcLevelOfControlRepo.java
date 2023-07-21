package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcLevelOfControl;

@Repository
public interface IqcLevelOfControlRepo extends JpaRepository<IqcLevelOfControl, Integer> {
}
