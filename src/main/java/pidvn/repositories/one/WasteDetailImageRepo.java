package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.WasteDetailImage;

@Repository
public interface WasteDetailImageRepo extends JpaRepository<WasteDetailImage, Integer> {
}
