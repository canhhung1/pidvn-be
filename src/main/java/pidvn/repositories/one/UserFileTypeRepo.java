package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserFileType;

@Repository
public interface UserFileTypeRepo extends JpaRepository<UserFileType, Integer> {
}
