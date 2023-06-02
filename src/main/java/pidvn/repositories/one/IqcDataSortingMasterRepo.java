package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcDataSortingMaster;

import java.util.List;

@Repository
public interface IqcDataSortingMasterRepo extends JpaRepository<IqcDataSortingMaster, Integer> {

    List<IqcDataSortingMaster> findByRequestNo(String requestNo);
}
