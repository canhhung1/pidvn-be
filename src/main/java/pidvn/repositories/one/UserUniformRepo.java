package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserUniform;

@Repository
public interface UserUniformRepo extends JpaRepository<UserUniform , Integer> {
}
