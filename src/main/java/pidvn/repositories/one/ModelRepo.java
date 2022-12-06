package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Model;

@Repository
public interface ModelRepo extends JpaRepository<Model, Integer> {
    Model findByName(String name);
}
