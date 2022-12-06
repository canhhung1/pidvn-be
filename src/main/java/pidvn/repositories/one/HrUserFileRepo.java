package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrUserFile;

import java.util.List;

@Repository
public interface HrUserFileRepo extends JpaRepository<HrUserFile, Integer> {
    List<HrUserFile> findByUsername(String username);
}
