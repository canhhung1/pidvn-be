package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc008;

@Repository
public interface IeDc008Repo extends JpaRepository<IeDc008, Integer> {
}
