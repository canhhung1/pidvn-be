package pidvn.modules.dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.dashboard.services.DashboardSvc;

import java.util.Date;

@RestController
@RequestMapping("Dashboard")
public class DashboardController {

    @Autowired
    private DashboardSvc dashboardSvc;

    @GetMapping("Traceability")
    public ResponseEntity<?> traceability(
            @RequestParam String lotNo, @RequestParam Date fromDate, @RequestParam Date toDate,
            @RequestParam String recordType
    ) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("ManufacturingQuantity")
    public ResponseEntity<?> getManufacturingQuantity() {
        return new ResponseEntity<>(this.dashboardSvc.getManufacturingQuantity(),HttpStatus.OK);
    }

}
