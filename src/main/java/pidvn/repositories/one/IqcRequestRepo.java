package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcRequest;

@Repository
public interface IqcRequestRepo extends JpaRepository<IqcRequest, Integer> {
    IqcRequest findByRequestNo(String requestNo);
}
