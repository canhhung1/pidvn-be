package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDrawing;

import java.util.List;

@Repository
public interface IeDrawingRepo extends JpaRepository<IeDrawing, String> {
    List<IeDrawing> findByProjectIdAndProgressId(Integer projectId, Integer progressId);
}
