package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.WasteDetailData;

import java.util.List;

@Repository
public interface WasteDetailDataRepo extends JpaRepository<WasteDetailData, Integer> {
    List<WasteDetailData> findByWasteMaster(Integer wasteMaster);
}
