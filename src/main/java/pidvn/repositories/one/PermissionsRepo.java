package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Permissions;

import java.util.List;

@Repository
public interface PermissionsRepo extends JpaRepository<Permissions, Integer> {
    List<Permissions> findByOrderByCreatedAtDesc();

}
