package pidvn.mappers.three.hr.meal;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import java.util.List;

@Mapper
public interface HrMealMapper {
    List<MealRecordVo> getMealRecords(SearchVo searchVo);
    List<MealRecordVo> getMealRecordsSummary(SearchVo searchVo);
}
