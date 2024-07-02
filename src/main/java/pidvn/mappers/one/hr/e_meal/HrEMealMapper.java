package pidvn.mappers.one.hr.e_meal;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.HrAttendanceDetail;
import pidvn.entities.one.HrMealRecord;
import pidvn.entities.one.HrOvertimeData;
import pidvn.modules.hr.meal.models.MealCouponVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface HrEMealMapper {

    void deleteEMealDataByIds(List<Integer> ids);
    List<MealCouponVo> getMealCouponData(Date month);

    int insertAttendanceDetails(List<HrAttendanceDetail> listAttendanceDetail);
    int insertOvertimeData(List<HrOvertimeData> listOvertimeData);
    int insertHrMealRecord(List<HrMealRecord> listMealRecord);
}
