package pidvn.modules.relay.relay_process_recording.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.commons.dto.ApiResponse;
import pidvn.modules.relay.relay_process_recording.models.LotDto;
import pidvn.modules.relay.relay_process_recording.models.RequestDto;
import pidvn.modules.relay.relay_process_recording.models.SearchDto;
import pidvn.modules.relay.relay_process_recording.services.RePrSvcImpl;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("Relay/ProcessRecording")
public class RePrCtrl {


    @Autowired
    private RePrSvcImpl rePrSvc;

    /**
     * Danh sách các phiếu xuất kho cho MA
     * @return
     */
    @PostMapping("Requests")
    public ResponseEntity<ApiResponse<?>> getRequests(@RequestBody SearchDto searchDto) {
        ApiResponse<List<RequestDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.getRequests(searchDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Lấy danh sách các Lots có trong phiếu xuất kho
     * @param requestNo
     * @return
     */
    @GetMapping("Request")
    public ResponseEntity<ApiResponse<?>> getRequestDetail(@RequestParam String requestNo) {
        ApiResponse<List<LotDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.getRequestDetail(requestNo));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Nhận NVL từ kho
     * Sau khi scan nhận sẽ lưu toàn bộ lot vào bảng pur_wh_records với record_type = 'RNP'
     * @param lots
     * @return
     */
    @PostMapping("ReceiveMaterials")
    public ResponseEntity<ApiResponse<?>> receiveMaterials(@RequestBody List<LotDto> lots) {
        ApiResponse<List<LotDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.receiveMaterials(lots));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Kiểm tra lot scan nhận NVL có thế lưu vào database ko
     * @param lotDto
     * @return
     */
    @PostMapping("ValidateLotReceive")
    public ResponseEntity<ApiResponse<?>> scanLotReceive(@RequestBody LotDto lotDto) {
        ApiResponse<LotDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.validateLotReceive(lotDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Xóa lot đã nhận
     * @param lotDto
     * @return
     */
    @PutMapping("DeleteLotReceived")
    public ResponseEntity<ApiResponse<?>> deleteLotReceived(@RequestBody LotDto lotDto) {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.deleteLotReceived(lotDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    /**
     * Chuyển vào xe NVL
     * @param lots
     * @return
     */
    @PostMapping("SendToLineWh")
    public ResponseEntity<ApiResponse<?>> sendToLineWh(@RequestBody List<LotDto> lots) {
        ApiResponse<List<LotDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.rePrSvc.sendToLineWh(lots));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
