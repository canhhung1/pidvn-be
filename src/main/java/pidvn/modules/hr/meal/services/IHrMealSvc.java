package pidvn.modules.hr.meal.services;

import pidvn.modules.hr.meal.models.SearchVo;

import java.util.Map;

public interface IHrMealSvc {

    Map getMealRecords(SearchVo searchVo) ;
}
