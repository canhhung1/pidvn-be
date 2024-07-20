package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc001;

@Repository
public interface IeDc001Repo extends JpaRepository<IeDc001, Integer> {
}
