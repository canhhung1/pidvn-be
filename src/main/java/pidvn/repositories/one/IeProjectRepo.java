package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeProject;

@Repository
public interface IeProjectRepo extends JpaRepository<IeProject, Integer> {
}
