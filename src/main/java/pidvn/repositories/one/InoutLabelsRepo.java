package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.InoutLabels;

import java.util.List;


@Repository
public interface InoutLabelsRepo extends JpaRepository<InoutLabels, Integer> {

    List<InoutLabels> findByOuterLotNo(@Param("outerLotNo") String outerLotNo);

    @Query("SELECT e FROM InoutLabels e WHERE e.outerLotNo IN (:outerLotNos)")
    List<InoutLabels> findByOuterLotNoIn(@Param("outerLotNos")List<String> outerLotNos);
}
