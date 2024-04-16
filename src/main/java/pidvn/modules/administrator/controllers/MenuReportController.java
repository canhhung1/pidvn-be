package pidvn.modules.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.administrator.services.MenuReportService;

@RestController
@RequestMapping("Administrator/Menu")
public class MenuReportController {

    @Autowired
    private MenuReportService menuReportService;

    @GetMapping("Report")
    public ResponseEntity<?> getMenu() {
        return new ResponseEntity<>(this.menuReportService.getMenuReport(), HttpStatus.OK);
    }
}
