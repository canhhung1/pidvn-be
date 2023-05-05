package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Default;

@Repository
public interface DefaultRepo extends JpaRepository<Default, Integer> {
    Default findByName(String name);
}
