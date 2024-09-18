package pidvn.modules.hr.meal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.hr.meal.models.EmailDataVo;
import pidvn.modules.hr.meal.models.SearchVo;
import pidvn.modules.hr.meal.services.HrMealSvc;
import reactor.util.annotation.Nullable;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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

    /**
     *
     * @param
     * @return
     */
    @PostMapping("GetUserSendEmail")
    public ResponseEntity<?> getUserSendEmail(@RequestPart("file") @Nullable MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.hrMealSvc.getUserSendEmail(file), HttpStatus.OK);
    }

    @PostMapping("SendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody List<EmailDataVo> data) throws IOException, MessagingException {
        return new ResponseEntity<>(this.hrMealSvc.sendEmail(data), HttpStatus.OK);
    }

}
