package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaMasterData;

@Repository
public interface MeaMasterDataRepo extends JpaRepository<MeaMasterData, Integer> {
}
