package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.UserUniformUpload;

import java.util.List;

@Repository
public interface UserUniformUploadRepo extends JpaRepository<UserUniformUpload, Integer> {

    @Query(value = "select distinct A.upload_id uploadId from user_uniform_upload A " +
                    "where DATE_FORMAT(A.created_at, '%Y-%m-%d') = DATE_FORMAT(curdate(), '%Y-%m-%d')",
            nativeQuery = true)
    List<String> getUploadIdsByCurDate();
}
