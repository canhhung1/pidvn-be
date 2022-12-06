package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.RelayInventory;

import java.util.List;

@Repository
public interface RelayInventoryRepo extends JpaRepository<RelayInventory, Integer> {

    List<RelayInventory> findByReqNoOrderByIdDesc(String reqNo);
    List<RelayInventory> findByReqNoAndLotNo(String reqNo, String lotNo);

}
