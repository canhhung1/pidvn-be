package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeProjectProgress;

@Repository
public interface IeProjectProgressRepo extends JpaRepository<IeProjectProgress, Integer> {
}
