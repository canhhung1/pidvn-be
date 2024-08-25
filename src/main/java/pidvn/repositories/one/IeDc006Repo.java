package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import pidvn.entities.one.IeDc006;

import java.util.List;

public interface IeDc006Repo extends JpaRepository<IeDc006, String> {
    List<IeDc006> findAllByProjectIdOrderByOrdinalAsc(Integer projectId);
}
