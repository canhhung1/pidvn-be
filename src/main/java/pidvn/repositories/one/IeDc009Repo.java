package pidvn.repositories.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.IeDc009;

import java.util.List;

@Repository
public interface IeDc009Repo extends JpaRepository<IeDc009, Integer> {
    List<IeDc009> findDistinctByProjectIdAndProjectProgressId(Integer projectId, Integer projectProgressId);

    IeDc009 findAllByNameAndProjectIdAndProjectProgressId(String name,Integer projectId,Integer projectProgressId);

}
