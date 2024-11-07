package pidvn.modules.relay.relay_process_recording.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidvn.commons.dto.ApiResponse;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.services.RePrSvcImpl;

import java.util.List;


@RestController
@RequestMapping("Relay/ProcessRecording")
public class RePrCtrl {


    @Autowired
    private RePrSvcImpl rePrSvc;

    @GetMapping("Requests")
    public ResponseEntity<ApiResponse<?>> getRequests() {
        ApiResponse<List<RequestDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.getRequests());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("Request")
    public ResponseEntity<ApiResponse<?>> getRequestDetail(@RequestParam String requestNo) {
        ApiResponse<List<LotDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.getRequestDetail(requestNo));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
