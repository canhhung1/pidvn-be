package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SiteMapRelay;

import java.util.List;

@Repository
public interface SiteMapRelayRepo extends JpaRepository<SiteMapRelay, Integer> {

    List<SiteMapRelay> findByType(Integer type);
    List<SiteMapRelay> findByTypeAndStatus(Integer type, Integer status);

}
