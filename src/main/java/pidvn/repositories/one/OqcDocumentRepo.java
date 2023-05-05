package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.OqcDocument;

@Repository
public interface OqcDocumentRepo extends JpaRepository<OqcDocument, Integer> {
}
