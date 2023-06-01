package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcDataSortingDetail;

import java.util.List;

@Repository
public interface IqcDataSortingDetailRepo extends JpaRepository<IqcDataSortingDetail, Integer> {
    List<IqcDataSortingDetail> findByRequestNo(String requestNo);
}
