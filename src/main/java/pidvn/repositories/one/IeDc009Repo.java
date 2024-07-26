package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc009;

@Repository
public interface IeDc009Repo extends JpaRepository<IeDc009, Integer> {
}
