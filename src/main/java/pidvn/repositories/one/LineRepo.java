package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Line;

import java.util.List;

@Repository
public interface LineRepo extends JpaRepository<Line, Integer> {

    List<Line> findByProductIdOrderByName(Integer productId);
}
