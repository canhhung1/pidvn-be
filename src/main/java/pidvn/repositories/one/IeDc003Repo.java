package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc003;

import java.util.List;

@Repository
public interface IeDc003Repo extends JpaRepository<IeDc003, Integer> {
    List<IeDc003> findAllByProjectId(int projectId);
}
