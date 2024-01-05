package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.MaterialControls;

import java.util.List;

@Repository
public interface MaterialControlsRepo extends JpaRepository<MaterialControls, Integer> {
    List<MaterialControls> findByIdIn(List<Integer> ids);
    List<MaterialControls> findByPlotnoAndRecordTypeOrderByIdDesc(String plotno, String recordType);
    List<MaterialControls> findByClotnoAndRecordType(String clotNo, String recordType);
    List<MaterialControls> findByPlotnoAndClotnoOrderByIdDesc(String plotno, String clotno);

}
