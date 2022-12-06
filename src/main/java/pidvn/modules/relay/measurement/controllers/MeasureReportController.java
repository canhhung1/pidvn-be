package pidvn.modules.relay.measurement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.modules.relay.measurement.models.report.MeaReportSearchVo;
import pidvn.modules.relay.measurement.models.report.MeaReportVo;
import pidvn.modules.relay.measurement.services.MeaReportService;

import java.util.List;

@RestController
@RequestMapping("Relay/Measurement/Report")
public class MeasureReportController {

    @Autowired
    private MeaReportService meaReportService;

    @PostMapping("AmountByLine")
    public ResponseEntity<?> getMeasurementAmountByLine(@RequestBody MeaReportSearchVo searchVo) {
        List<MeaReportVo> result = this.meaReportService.getMeasurementAmountByLine(searchVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
