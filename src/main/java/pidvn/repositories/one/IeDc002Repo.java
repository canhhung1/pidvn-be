package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc002;

@Repository
public interface IeDc002Repo extends JpaRepository<IeDc002, Integer> {
}
