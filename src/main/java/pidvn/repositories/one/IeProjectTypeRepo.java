package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeProjectType;

@Repository
public interface IeProjectTypeRepo extends JpaRepository<IeProjectType, Integer> {
}
