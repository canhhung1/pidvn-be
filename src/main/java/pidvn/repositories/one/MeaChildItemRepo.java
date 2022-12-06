package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MeaChildItem;

import java.util.List;

@Repository
public interface MeaChildItemRepo extends JpaRepository<MeaChildItem, Integer> {
    List<MeaChildItem> findByItem(Integer item);

    List<MeaChildItem> findByItemAndModelType(Integer item, String modelType);
}
