package pidvn.modules.hr.meal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.modules.hr.meal.services.HrMealSvc;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("HR/Meal")
public class HrMealCtl {

    @Autowired
    private HrMealSvc hrMealSvc;

    /**
     * Lấy dữ lệu tính toán vé ăn từ timesheet từ PV Database
     * @return
     */
    @PostMapping("TimesheetConfirm")
    public ResponseEntity<?> timesheetConfirm(@RequestParam String table) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(this.hrMealSvc.timesheetConfirm(table), HttpStatus.OK);
    }

    @PostMapping("MealRecords")
    public ResponseEntity<?> getMealRecords(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.hrMealSvc.getMealRecords(searchVo), HttpStatus.OK);
    }

    @PostMapping("Balance")
    public ResponseEntity<?> getBalance(@RequestBody Date month) {
        return new ResponseEntity<>(this.hrMealSvc.getBalance(month), HttpStatus.OK);
    }

}
