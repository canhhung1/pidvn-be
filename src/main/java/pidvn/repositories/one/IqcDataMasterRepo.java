package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcDataMaster;

import java.util.List;

@Repository
public interface IqcDataMasterRepo extends JpaRepository<IqcDataMaster, Integer> {
    List<IqcDataMaster> findByRequestNoAndLotGroup(String requestNo, String lotGroup);
}
