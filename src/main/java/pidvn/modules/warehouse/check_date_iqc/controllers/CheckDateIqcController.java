package pidvn.modules.warehouse.check_date_iqc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.warehouse.check_date_iqc.models.CheckDateIqcVo;
import pidvn.modules.warehouse.check_date_iqc.services.CheckDateIqcService;

@RestController
@RequestMapping("WH/Check_date_iqc")
public class CheckDateIqcController {
    @Autowired
    private CheckDateIqcService checkDateIqcService;
    @PostMapping("Check")
    public ResponseEntity<?> getCheckLotNoIqc(@RequestBody CheckDateIqcVo searchVo) {
        return new ResponseEntity<>(this.checkDateIqcService.getCheckLotNoIqc(searchVo), HttpStatus.OK);
    }
}
