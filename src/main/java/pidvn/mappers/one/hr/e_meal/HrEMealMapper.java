package pidvn.mappers.one.hr.e_meal;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrEMealMapper {

    void deleteEMealDataByIds(List<Integer> ids);
}
