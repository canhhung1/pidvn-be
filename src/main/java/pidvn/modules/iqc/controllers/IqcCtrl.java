package pidvn.modules.iqc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.entities.one.IqcLevelOfControl;
import pidvn.modules.iqc.models.*;
import pidvn.modules.iqc.services.IqcSvcImpl;

import java.util.List;
import java.util.Map;

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

    @GetMapping("IqcRequest/{requestNo}")
    public ResponseEntity<ApiResponse<?>> getIqcRequest(@PathVariable String requestNo) {
        ApiResponse<IqcRequestDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getIqcRequest(requestNo));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("IqcRequest")
    public ResponseEntity<ApiResponse<?>> createIqcRequest(@RequestBody IqcRequestDto iqcRequestDto) {
        ApiResponse<Map> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.createIqcRequest(iqcRequestDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("IqcRequest")
    public ResponseEntity<ApiResponse<?>> updateIqcRequest(@RequestBody IqcRequestDto iqcRequestDto) {
        ApiResponse<IqcRequestDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.updateIqcRequest(iqcRequestDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("SlipNo")
    public ResponseEntity<ApiResponse<?>> getSlipNo() {
        ApiResponse<List<PurWhRecordDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getSlipNo());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("IqcResults")
    public ResponseEntity<ApiResponse<?>> getIqcResults(@RequestParam String requestNo) {
        ApiResponse<List<IqcResultDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getIqcResults(requestNo));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("IqcLevelOfControls")
    public ResponseEntity<ApiResponse<?>> getIqcLevelOfControls() {
        ApiResponse<List<IqcLevelOfControl>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getIqcLevelOfControls());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("EvaluateLotNos")
    public ResponseEntity<ApiResponse<?>> evaluateLotNos(@RequestBody List<IqcResultDto> iqcResults) {
        ApiResponse<List<IqcResultDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.evaluateLotNos(iqcResults));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("InventoryLots")
    public ResponseEntity<ApiResponse<?>> getLotsInventory() {
        ApiResponse<List<PihStoreDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getLotsInventory());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
