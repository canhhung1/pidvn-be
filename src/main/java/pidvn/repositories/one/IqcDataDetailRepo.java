package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcDataDetail;

import java.util.List;

@Repository
public interface IqcDataDetailRepo extends JpaRepository<IqcDataDetail, Integer>  {
    List<IqcDataDetail> findByRequestNoAndLotNo(String requestNo, String lotNo);
    void deleteById(Integer id);
}
