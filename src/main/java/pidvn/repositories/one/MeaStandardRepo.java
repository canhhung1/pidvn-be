package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaStandard;

import java.util.List;

@Repository
public interface MeaStandardRepo extends JpaRepository<MeaStandard, Integer> {
    List<MeaStandard> findByItemAndModelType(Integer item, String modelType);
}
