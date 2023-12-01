package pidvn.modules.hr.meal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.modules.hr.meal.services.HrMealSvc;

@RestController
@RequestMapping("HR/Meal")
public class HrMealCtl {

    @Autowired
    private HrMealSvc hrMealSvc;

    @PostMapping("MealRecords")
    public ResponseEntity<?> getMealRecords(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.hrMealSvc.getMealRecords(searchVo), HttpStatus.OK);
    }

    @GetMapping("Balance")
    public ResponseEntity<?> getBalance() {
        return new ResponseEntity<>(this.hrMealSvc.getBalance(), HttpStatus.OK);
    }

}
