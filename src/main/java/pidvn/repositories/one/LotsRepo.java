package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Lots;

import java.util.List;

@Repository
public interface LotsRepo extends JpaRepository<Lots, Integer> {
    Lots findByLotNo(String lotNo);

    List<Lots> findByLotNoIn(List<String> lotNos);
}
