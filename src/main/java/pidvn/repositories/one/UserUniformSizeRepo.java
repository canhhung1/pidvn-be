package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserUniformSize;

@Repository
public interface UserUniformSizeRepo extends JpaRepository<UserUniformSize, Integer> {
    void deleteByUsername(String username);
}
