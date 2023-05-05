package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.HrCourseGroup;

@Repository
public interface HrCourseGroupsRepo extends JpaRepository<HrCourseGroup,Integer> {

}
