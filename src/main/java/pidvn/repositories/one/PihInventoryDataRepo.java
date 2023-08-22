package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PihInventoryData;

import java.util.List;

@Repository
public interface PihInventoryDataRepo extends JpaRepository<PihInventoryData, Integer> {

    List<PihInventoryData> findByRequestId(Integer requestId);
}
