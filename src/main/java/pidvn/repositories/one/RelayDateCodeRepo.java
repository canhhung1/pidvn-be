package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.RelayDateCode;

import java.util.List;

@Repository
public interface RelayDateCodeRepo extends JpaRepository<RelayDateCode, Integer> {

    List<RelayDateCode> findByQaCardAndDateCode(String qaCard, String dateCode);


    List<RelayDateCode> findByQaCardAndDateCodeAndCustomerCode(String qaCard, String dateCode, String customerCode);
}
