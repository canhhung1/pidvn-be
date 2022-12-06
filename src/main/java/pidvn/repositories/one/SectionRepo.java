package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Section;

@Repository
public interface SectionRepo extends JpaRepository<Section, Integer> {
}
