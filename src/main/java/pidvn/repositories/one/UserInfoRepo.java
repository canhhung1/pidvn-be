package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUsername(String username);
}
