package pidvn.modules.hr.meal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.modules.hr.meal.services.HrMealSvc;

import java.util.Date;

@RestController
@RequestMapping("HR/Meal")
public class HrMealCtl {

    @Autowired
    private HrMealSvc hrMealSvc;

    @PostMapping("MealRecords")
    public ResponseEntity<?> getMealRecords(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.hrMealSvc.getMealRecords(searchVo), HttpStatus.OK);
    }

    @PostMapping("Balance")
    public ResponseEntity<?> getBalance(@RequestBody Date month) {
        return new ResponseEntity<>(this.hrMealSvc.getBalance(month), HttpStatus.OK);
    }

}
