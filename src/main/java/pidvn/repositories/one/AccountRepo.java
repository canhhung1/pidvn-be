package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);

}
