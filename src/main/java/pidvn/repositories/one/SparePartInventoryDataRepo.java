package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartInventoryData;

@Repository
public interface SparePartInventoryDataRepo extends JpaRepository<SparePartInventoryData, Integer> {
}
