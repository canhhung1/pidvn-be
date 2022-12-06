package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaItemImage;

import java.util.List;

@Repository
public interface MeaItemImageRepo extends JpaRepository<MeaItemImage, Integer> {
    List<MeaItemImage> findByItem(Integer item);
    List<MeaItemImage> findByItemAndModelType(Integer item, String modelType);
}
