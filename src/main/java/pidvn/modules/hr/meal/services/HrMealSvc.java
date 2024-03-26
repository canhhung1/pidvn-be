package pidvn.modules.hr.meal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.entities.one.EMealData;
import pidvn.entities.one.HrAttendanceDetail;
import pidvn.entities.one.HrMealRecord;
import pidvn.entities.one.HrOvertimeData;
import pidvn.mappers.one.hr.e_meal.HrEMealMapper;
import pidvn.mappers.three.hr.meal.HrMealMapper;
import pidvn.modules.hr.meal.models.MealCouponVo;
import pidvn.modules.hr.meal.models.MealRecordVo;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.repositories.one.EMealDataRepo;
import pidvn.repositories.one.HrAttendanceDetailRepo;
import pidvn.repositories.one.HrMealRecordRepo;
import pidvn.repositories.one.HrOvertimeDataRepo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class HrMealSvc implements IHrMealSvc {

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
    public Map timesheetConfirm() {

        /**
         * Xóa data AttendanceDetails tháng trước đó ở FDSC db
         */
        //this.hrAttendanceDetailRepo.deleteAttendanceDetailsPreviousMonth();

        /**
         * Xóa data Overtime tháng trước đó ở FDSC db
         */
        //this.hrOvertimeDataRepo.deleteOvertimeDataPreviousMonth();

        /**
         * Xóa data MealRecord tháng trước đó ở FDCS db
         */
        this.hrMealRecordRepo.deleteMealRecordsPreviousMonth();

        /**
         * Lấy dữ liệu AttendanceDetails, Overtime, MealRecord từ PVG database
         */
        //List<HrAttendanceDetail> attendanceDetails = this.hrMealMapper.getAttendanceDetails();
        //List<HrOvertimeData> overtimeData = this.hrMealMapper.getOvertimeData();
        List<HrMealRecord> mealRecords = this.hrMealMapper.getHrMealRecord();

        /**
         * Lưu dữ liệu AttendanceDetails, Overtime, MealRecord vào FDCS database
         */
        //List<HrAttendanceDetail> attendanceDetailResult = this.hrAttendanceDetailRepo.saveAll(attendanceDetails);
        //List<HrOvertimeData> overtimeResult = this.hrOvertimeDataRepo.saveAll(overtimeData);
        List<HrMealRecord> mealRecordResult = this.hrMealRecordRepo.saveAll(mealRecords);

        /**
         * Lưu dữ liệu MealRecords
         */

        Map map = new HashMap();
        //map.put("attendanceDetailResult", attendanceDetailResult.size());
        //map.put("overtimeResult", overtimeResult.size());
        map.put("mealRecordResult", mealRecordResult.size());


        return map;
    }
}
