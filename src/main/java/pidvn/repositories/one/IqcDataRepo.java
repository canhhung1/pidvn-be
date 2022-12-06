package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IqcData;

@Repository
public interface IqcDataRepo extends JpaRepository<IqcData, Integer> {

    @Query("select i from IqcData i where i.requestNo = :requestNo and i.invoice = :invoice and i.lotNo = :lotNo")
    IqcData findByRequestNoAndInvoiceAndLotNo(@Param("requestNo") String requestNo, @Param("invoice") String invoice, @Param("lotNo") String lotNo);
}
