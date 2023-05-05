package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PidvnStdDummy;

import java.util.List;

@Repository
public interface PidvnStdDummyRepo extends JpaRepository<PidvnStdDummy, Integer> {

    List<PidvnStdDummy> findByFlag(Integer flag);
}
