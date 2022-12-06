package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.InspectQaCard;

import java.util.List;

@Repository
public interface InspectQaCardRepo extends JpaRepository<InspectQaCard, Integer> {
    List<InspectQaCard> findByInspectQaCard(String inspectQaCard);
}
