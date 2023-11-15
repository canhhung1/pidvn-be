package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartInventoryData;

import java.util.List;

@Repository
public interface SparePartInventoryDataRepo extends JpaRepository<SparePartInventoryData, Integer> {
}
