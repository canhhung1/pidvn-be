package pidvn.modules.hr.meal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.auth.controller.AuthController;
import pidvn.entities.one.HrAttendanceDetail;
import pidvn.entities.one.HrLeaveDay;
import pidvn.entities.one.HrMealRecord;
import pidvn.entities.one.HrOvertimeData;
import pidvn.mappers.one.hr.e_meal.HrEMealMapper;
import pidvn.mappers.three.hr.meal.HrMealMapper;
import pidvn.modules.hr.meal.models.MealCouponVo;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.repositories.one.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HrMealSvc implements IHrMealSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(HrMealSvc.class);

    @Autowired
    private HrMealMapper hrMealMapper;

    @Autowired
    private HrEMealMapper hrEMealMapperDB1;

    @Autowired
    private EMealDataRepo eMealDataRepo;

    @Autowired
    private HrAttendanceDetailRepo hrAttendanceDetailRepo;

    @Autowired
    private HrOvertimeDataRepo hrOvertimeDataRepo;

    @Autowired
    private HrMealRecordRepo hrMealRecordRepo;

    @Autowired
    private HrLeaveDayRepo hrLeaveDayRepo;


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
    public List<MealCouponVo> getBalance(Date month) {
        return this.hrEMealMapperDB1.getMealCouponData(month);
    }


    @Override
    @Transactional(transactionManager = "transactionManagerOne")
    public Map timesheetConfirm(String table) {

        Map result = new HashMap();

        try {
            if (table.equals("attendance")) {
                // Lấy data từ PVG
                List<HrAttendanceDetail> attendanceDetails = this.hrMealMapper.getAttendanceDetails();

                // Xóa data
                this.hrAttendanceDetailRepo.deleteAttendanceDetailsPreviousMonth();

                // Lưu data vào MySQL
                List<HrAttendanceDetail> attendanceDetailResult = this.hrAttendanceDetailRepo.saveAll(attendanceDetails);

                result.put("table", "attendance");
                result.put("record", attendanceDetailResult.size());
            }

            if (table.equals("overtime")) {
                // Lấy data từ PVG
                List<HrOvertimeData> overtimeData = this.hrMealMapper.getOvertimeData();

                // Xóa data
                this.hrOvertimeDataRepo.deleteOvertimeDataPreviousMonth();

                // Lưu data vào MySQL
                List<HrOvertimeData> overtimeResult = this.hrOvertimeDataRepo.saveAll(overtimeData);
                result.put("table", "overtime");
                result.put("record", overtimeResult.size());
            }

            if (table.equals("meal_record")) {
                // Lấy data từ PVG
                List<HrMealRecord> mealRecords = this.hrMealMapper.getHrMealRecord();

                // Xóa data
                this.hrMealRecordRepo.deleteMealRecordsPreviousMonth();

                // Lưu data vào MySQL
                List<HrMealRecord> mealRecordResult = this.hrMealRecordRepo.saveAll(mealRecords);

                result.put("table", "meal_record");
                result.put("record", mealRecordResult.size());
            }

            if(table.equals("leave_day")) {
                // Lấy data từ PVG
                List<HrLeaveDay> leaveDays = this.hrMealMapper.getHrLeaveDay();

                // Xóa data
                this.hrLeaveDayRepo.deleteLeaveDayPreviousMonth();

                // Lưu data vào MySQL
                List<HrLeaveDay> leaveDaysResult = this.hrLeaveDayRepo.saveAll(leaveDays);

                result.put("table", "leave_day");
                result.put("record", leaveDaysResult.size());
            }
        }catch (Exception e) {
            LOGGER.debug("GET DATA TABLE : " + table);
            LOGGER.debug("ERROR GET DATA TABLE : " + e.toString());
        }



        return result;
    }
}
