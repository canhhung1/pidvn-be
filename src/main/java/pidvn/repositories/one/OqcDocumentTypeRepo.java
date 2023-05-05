package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.OqcDocumentType;

@Repository
public interface OqcDocumentTypeRepo extends JpaRepository<OqcDocumentType, Integer> {
}
