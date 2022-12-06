package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserFile;

import java.util.List;

@Repository
public interface UserFileRepo extends JpaRepository<UserFile, Integer> {
    List<UserFile> findByUsername(String username);
}
