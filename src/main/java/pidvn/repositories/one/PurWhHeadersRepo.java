package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.PurWhHeaders;

import java.util.Date;
import java.util.List;

@Repository
public interface PurWhHeadersRepo extends JpaRepository<PurWhHeaders, Integer> {

    PurWhHeaders findByRegNo(String regNo);
    List<PurWhHeaders> findByTypeAndDate(String recordType, Date date);
}
