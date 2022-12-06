package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.RelayWhRecords;

import java.util.List;

@Repository
public interface RelayWhRecordsRepo extends JpaRepository<RelayWhRecords, Integer> {
    List<RelayWhRecords> findByLotNoOrderByIdDesc(String lotNo);
    List<RelayWhRecords> findBySlipNoAndRecordType(String slipNo, String recordType);
    List<RelayWhRecords> findByLotNoAndRecordTypeInOrderByIdDesc(String lotNo, List<String> recordTypes);
}
