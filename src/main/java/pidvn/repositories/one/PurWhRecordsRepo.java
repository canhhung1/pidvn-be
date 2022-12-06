package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PurWhRecords;

import java.util.Date;
import java.util.List;

@Repository
public interface PurWhRecordsRepo extends JpaRepository<PurWhRecords, Integer> {
    List<PurWhRecords> findByInvoice(String invoice);
    List<PurWhRecords> findByLotNoAndRecordTypeInOrderByIdDesc(String lotNo, List<String> recordTypes);
    List<PurWhRecords> findByRecordTypeAndDate(String recordType, Date date);
}
