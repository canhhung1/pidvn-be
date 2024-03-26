package pidvn.mappers.one.hr.e_meal;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.hr.meal.models.MealCouponVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface HrEMealMapper {

    void deleteEMealDataByIds(List<Integer> ids);
    List<MealCouponVo> getMealCouponData(Date month);
}
