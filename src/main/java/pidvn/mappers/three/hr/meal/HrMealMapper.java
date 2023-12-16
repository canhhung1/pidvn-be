package pidvn.mappers.three.hr.meal;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.EMealData;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface HrMealMapper {
    List<MealRecordVo> getMealRecords(SearchVo searchVo);
    List<MealRecordVo> getMealRecordsSummary(SearchVo searchVo);

    // List<MealRecordVo> getBalance();


    /**
     * Tính toán để lấy số lượng vé ăn theo dữ liệu timesheet,
     * dữ liệu OT, và dữ liệu thực tế user đã scan
     * @param month tháng cần lấy dữ liệu
     * @return
     */
    List<EMealData> getAmountTicketByTimeSheetAndActualUserScan(Date month);
}
