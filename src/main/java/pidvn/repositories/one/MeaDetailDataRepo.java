package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaDetailData;

@Repository
public interface MeaDetailDataRepo extends JpaRepository<MeaDetailData, Integer> {
}
