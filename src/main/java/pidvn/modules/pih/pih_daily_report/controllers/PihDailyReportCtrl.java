package pidvn.modules.pih.pih_daily_report.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.entities.one.PihDailyMeetingReport;
import pidvn.modules.pih.pih_daily_report.services.PihDailyReportSvc;

import java.util.Date;

@RestController
@RequestMapping("PIH/DailyReport")
public class PihDailyReportCtrl {

    @Autowired
    private PihDailyReportSvc pihDailyReportSvc;

    @PostMapping("DailyReportData")
    public ResponseEntity<?> getDailyReportData(@RequestBody Date date) {
        return new ResponseEntity<>(this.pihDailyReportSvc.getDailyReportData(date), HttpStatus.OK);
    }

    @PostMapping("SaveDailyReportData")
    public ResponseEntity<?> saveDailyReportData(@RequestBody PihDailyMeetingReport data) {
        return new ResponseEntity<>(this.pihDailyReportSvc.saveDailyReportData(data), HttpStatus.OK);
    }
}
