package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.WasteHandleCompany;

@Repository
public interface WasteHandleCompanyRepo extends JpaRepository<WasteHandleCompany, Integer> {
}
