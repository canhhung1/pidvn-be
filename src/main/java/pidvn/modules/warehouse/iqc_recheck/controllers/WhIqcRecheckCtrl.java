package pidvn.modules.warehouse.iqc_recheck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.commons.dto.ApiResponse;
import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.Lots;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;
import pidvn.modules.warehouse.iqc_recheck.models.RequestDto;
import pidvn.modules.warehouse.iqc_recheck.services.WhIqcRecheckSvc;

import java.util.List;

@RestController
@RequestMapping("WH/IQC-Recheck")
public class WhIqcRecheckCtrl {

    @Autowired
    private WhIqcRecheckSvc whIqcRecheckSvc;

    @PostMapping("Request")
    public ResponseEntity<ApiResponse<?>> createIqcRecheckRequest(@RequestBody RequestDto requestDto) {
        ApiResponse<IqcRequest> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.whIqcRecheckSvc.createIqcRecheckRequest(requestDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("ScanLabel")
    public ResponseEntity<ApiResponse<?>> scanLabel(@RequestBody LabelDto labelDto) {
        ApiResponse<List<Lots>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.whIqcRecheckSvc.scanLabel(labelDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
