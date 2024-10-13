package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcResults;

@Repository
public interface IqcResultsRepo extends JpaRepository<IqcResults, Integer> {
}
