package pidvn.repositories.one;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidvn.entities.one.EMealData;

import java.util.Date;
import java.util.List;

@Repository
public interface EMealDataRepo extends JpaRepository<EMealData, Integer> {

    @Query(value = "SELECT * FROM e_meal_data where 1 = 1 AND DATE_FORMAT(date, \"%Y-%m\") = DATE_FORMAT(:month, \"%Y-%m\")", nativeQuery = true)
    List<EMealData> getDataByMonth(@Param("month") Date month);


    @Modifying
    @Query(value = "DELETE FROM e_meal_data WHERE id IN (:ids)", nativeQuery = true)
    void deleteByIds(@Param("ids") List<Integer> ids);

}
