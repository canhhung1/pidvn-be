package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.StopItems;

import java.util.List;

@Repository
public interface StopItemsRepo extends JpaRepository<StopItems, Integer> {

    @Query("SELECT s FROM StopItems s WHERE s.disable <> :value OR s.disable IS NULL")
    List<StopItems> findByDisableNot(@Param("value") Integer value);


}
