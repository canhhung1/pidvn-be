package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
}
