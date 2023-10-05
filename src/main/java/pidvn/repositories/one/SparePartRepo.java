package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.SparePart;

import java.util.List;

@Repository
public interface SparePartRepo extends JpaRepository<SparePart, Integer> {

    List<SparePart> findAllByOrderByIdDesc();
    SparePart findByPartNumber(String partNumber);
    SparePart findByGalileoName(String galileoName);
}
