package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Users;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.username LIKE %:username%")
    List<Users> findByUsernameContains(@Param("username") String username);

    Users findByEmail(String email);
}
