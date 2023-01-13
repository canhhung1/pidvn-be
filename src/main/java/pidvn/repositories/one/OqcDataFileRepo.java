package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.OqcDataFile;

import java.util.List;

@Repository
public interface OqcDataFileRepo extends JpaRepository<OqcDataFile, Integer> {
    List<OqcDataFile> findByReqNoOrderByIdDesc(String reqNo);
}
