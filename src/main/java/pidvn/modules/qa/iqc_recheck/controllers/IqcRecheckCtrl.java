package pidvn.modules.qa.iqc_recheck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.entities.one.IqcRequest;
import pidvn.modules.qa.iqc_recheck.models.LotDto;
import pidvn.modules.qa.iqc_recheck.services.IqcRecheckSvcImpl;

import java.util.List;

@RestController
@RequestMapping("QA/IQC-Recheck")
public class IqcRecheckCtrl {

    @Autowired
    private IqcRecheckSvcImpl iqcRecheckSvc;

    /**
     * Danh sách các lot trong Request
     * @return
     */
    @GetMapping("Requests/{requestNo}")
    public ResponseEntity<ApiResponse<?>> getRequest(@PathVariable String requestNo, @RequestParam String goodsType) {
        ApiResponse<List<LotDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.iqcRecheckSvc.getLotsInRequest(requestNo, goodsType));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
