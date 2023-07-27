package pidvn.modules.hr.meal.services;

import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import java.util.List;

public interface IHrMealSvc {
    List<MealRecordVo> getMealRecords(SearchVo searchVo);
}
