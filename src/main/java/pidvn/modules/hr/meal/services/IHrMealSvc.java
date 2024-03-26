package pidvn.modules.hr.meal.services;

import pidvn.entities.one.EMealData;
import pidvn.modules.hr.meal.models.MealCouponVo;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IHrMealSvc {

    Map getMealRecords(SearchVo searchVo) ;

    List<MealCouponVo> getBalance(Date month);

    Map timesheetConfirm();

}
