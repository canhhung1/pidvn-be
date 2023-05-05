package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PidvnStkPayment;

@Repository
public interface PidvnStkPaymentRepo extends JpaRepository<PidvnStkPayment, Integer> {
}
