package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.WasteGroup;

@Repository
public interface WasteGroupRepo extends JpaRepository<WasteGroup, Integer> {
}
