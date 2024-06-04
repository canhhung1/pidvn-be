package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePartRequestDetail;

import java.util.List;

@Repository
public interface SparePartRequestDetailRepo extends JpaRepository<SparePartRequestDetail, Integer> {

    List<SparePartRequestDetail> findByRequestId(Integer requestId);
}
