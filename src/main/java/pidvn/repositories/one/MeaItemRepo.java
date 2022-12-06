package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaItem;

@Repository
public interface MeaItemRepo extends JpaRepository<MeaItem, Integer> {
}
