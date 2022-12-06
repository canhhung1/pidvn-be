package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.StopItems;

import java.util.List;

@Repository
public interface StopItemsRepo extends JpaRepository<StopItems, Integer> {
}
