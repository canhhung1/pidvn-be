package pidvn.modules.hr.meal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.EMealData;
import pidvn.mappers.three.hr.meal.HrMealMapper;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.repositories.one.EMealDataRepo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HrMealSvc implements IHrMealSvc {

    @Autowired
    private HrMealMapper hrMealMapper;

    @Autowired
    private EMealDataRepo eMealDataRepo;

    @Override
    public Map getMealRecords(SearchVo searchVo) {
        Map result = new HashMap();

        List<MealRecordVo> records = this.hrMealMapper.getMealRecords(searchVo);
        List<MealRecordVo> recordsSummary = this.hrMealMapper.getMealRecordsSummary(searchVo);

        result.put("records", records);
        result.put("recordsSummary", recordsSummary);

        return result;
    }

    @Override
    public List<EMealData> getBalance(Date month) {
        return this.hrMealMapper.getAmountTicketByTimeSheetAndActualUserScan(month);
    }

    @Override
    public List<EMealData> timesheetConfirm() {

        // B2: Tổng hợp dữ liệu timesheet từ PVG database

        Date date = new Date();
        date.setMonth(date.getMonth() - 1);
        List<EMealData> data = this.hrMealMapper.getAmountTicketByTimeSheetAndActualUserScan(date);
        
        List<EMealData> oldDataTimeSheet = this.eMealDataRepo.getDataByMonth(date);
        
        if (data.size() > 0) {
            /**
             * Xóa dữ liệu timesheet cũ trong DB: pidvn
             */
            this.eMealDataRepo.deleteAll(oldDataTimeSheet);

            /**
             * Lưu dữ liệu mới lấy đc từ DB: PVG
             */
            return this.eMealDataRepo.saveAll(data);
        }

        return oldDataTimeSheet;
    }
}
