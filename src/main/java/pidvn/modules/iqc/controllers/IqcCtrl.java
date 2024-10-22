package pidvn.modules.iqc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.modules.iqc.models.IqcRequestDto;
import pidvn.modules.iqc.models.PurWhRecordDto;
import pidvn.modules.iqc.models.SearchDto;
import pidvn.modules.iqc.services.IqcSvcImpl;

import java.util.List;

@RestController
@RequestMapping("IQC")
public class IqcCtrl {

    @Autowired
    private IqcSvcImpl iqcSvc;

    @PostMapping("IqcRequests")
    public ResponseEntity<ApiResponse<?>> getIqcRequests(@RequestBody SearchDto searchDto) {
        searchDto.setDateRange(null);
        ApiResponse<List<IqcRequestDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getIqcRequests(searchDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("IqcRequest")
    public ResponseEntity<ApiResponse<?>> createRequest(@RequestBody SearchDto searchDto) {


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("SlipNo")
    public ResponseEntity<ApiResponse<?>> getSlipNo() {
        ApiResponse<List<PurWhRecordDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getSlipNo());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
