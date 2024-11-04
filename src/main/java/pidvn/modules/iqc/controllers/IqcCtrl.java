package pidvn.modules.iqc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.entities.one.IqcLevelOfControl;
import pidvn.entities.one.Users;
import pidvn.modules.iqc.models.*;
import pidvn.modules.iqc.services.IqcSvcImpl;
import pidvn.modules.iqc.utils.IqcExporter;
import pidvn.repositories.one.UsersRepo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("IQC")
public class IqcCtrl {

    @Autowired
    private IqcSvcImpl iqcSvc;

    @PostMapping("IqcRequests")
    public ResponseEntity<ApiResponse<?>> getIqcRequests(@RequestBody SearchDto searchDto) {
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
    public ResponseEntity<ApiResponse<?>> createIqcRequest(@RequestBody IqcRequestDto iqcRequestDto) throws Exception {
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

    @GetMapping("HistoryLevelOfControls")
    public ResponseEntity<ApiResponse<?>> getHistoryLevelOfControls(@RequestParam String model) {
        ApiResponse<List<IqcResultDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getHistoryLevelOfControls(model));
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
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.getLotsInventory());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    /**
     * Chuẩn bị data để tạo rquest IQC
     * @param searchDto
     * @return
     */
    @PostMapping("PrepareDataCreateRequest")
    public ResponseEntity<ApiResponse<?>> prepareDataCreateRequest(@RequestBody SearchDto searchDto) {
        ApiResponse<List<PihStoreDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcSvc.prepareDataCreateRequest(searchDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("ExportExcel")
    public ResponseEntity<?> exportExcel(@RequestBody IqcRequestDto iqcRequestDto) throws IOException {

        List<IqcResultDto> data = this.iqcSvc.getIqcResultsExportExcel(iqcRequestDto.getRequestNo());
        IqcExporter exporter = new IqcExporter(data);
        ByteArrayInputStream inputStream = exporter.export();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=IqcData.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));

    }




}
