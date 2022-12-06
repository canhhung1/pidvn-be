package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.TmpAdrTnmu010;

import java.util.List;

@Repository
public interface TmpAdrTnmu010Repo extends JpaRepository<TmpAdrTnmu010, Integer> {

    List<TmpAdrTnmu010> findByPnpa(String pnpa);
}
