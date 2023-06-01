package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcRequestSortingDetail;

@Repository
public interface IqcRequestSortingDetailRepo extends JpaRepository<IqcRequestSortingDetail, Integer> {
}
