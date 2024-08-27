package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc006;

import java.util.List;

@Repository
public interface IeDc006Repo extends JpaRepository<IeDc006, String> {
    List<IeDc006> findAllByProjectIdOrderByOrdinalAsc(Integer projectId);
    List<IeDc006> findAllByProjectIdAndDrawingNoIn(Integer projectId, List<String> drawingNoList);
}
