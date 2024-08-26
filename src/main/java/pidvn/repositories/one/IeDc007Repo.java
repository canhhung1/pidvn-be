package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc007;

import java.util.List;

@Repository
public interface IeDc007Repo extends JpaRepository<IeDc007, Integer> {
    List<IeDc007> findAllByProjectId(Integer projectId);
}
