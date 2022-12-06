package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.WasteType;

import java.util.List;

@Repository
public interface WasteTypeRepo extends JpaRepository<WasteType, Integer> {
    List<WasteType> findByWasteGroup(Integer group);
}
