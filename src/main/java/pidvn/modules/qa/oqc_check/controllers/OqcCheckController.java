package pidvn.modules.qa.oqc_check.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.qa.oqc_check.models.OqcRequestVo;
import pidvn.modules.qa.oqc_check.models.SearchVo;
import pidvn.modules.qa.oqc_check.services.OqcCheckSvc;
import reactor.util.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("QA/OqcCheck")
public class OqcCheckController {

    private String FILE_PATH_ROOT = "P:\\IS\\CanhHung\\FDCS\\QA\\OQC\\Relay\\";

    @Autowired
    private OqcCheckSvc oqcCheckSvc;

    @GetMapping("MasterData")
    public ResponseEntity<?> getOqcMasterData(@RequestParam String reqNo, @RequestParam String qaCard) {
        return new ResponseEntity<>(this.oqcCheckSvc.getOqcMasterData(reqNo,qaCard), HttpStatus.OK);
    }

    /**
     * Tìm kiếm Request theo phương thức post, truyền params bằng @RequestBody
     * @return
     */
    @PostMapping("Requests")
    public ResponseEntity<?> getOqcRequests(@RequestBody SearchVo searchVo) {

        return new ResponseEntity<>(this.oqcCheckSvc.getOqcRequests(searchVo), HttpStatus.OK);
    }

    @PutMapping("Request")
    public ResponseEntity<?> updateOqcRequest(@RequestBody OqcRequestVo oqcRequestVo) {
        return new ResponseEntity<>(this.oqcCheckSvc.updateOqcRequest(oqcRequestVo), HttpStatus.OK);
    }

    @PostMapping("RequestHandle")
    public ResponseEntity<?> oqcRequestHandle(@RequestBody OqcRequestVo oqcRequestVo) {
        return new ResponseEntity<>(this.oqcCheckSvc.oqcRequestHandle(oqcRequestVo), HttpStatus.OK);
    }


    @PostMapping("UploadFile")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file, @RequestParam String createdBy, @RequestParam String reqNo ) throws IOException {
        return new ResponseEntity<>(this.oqcCheckSvc.uploadFile(file, createdBy, this.FILE_PATH_ROOT, reqNo), HttpStatus.OK);
    }

    /**
     * Method này dùng để lấy ra các file đo đạc được attach vào OqcRequest
     * @param reqNo
     * @return
     */
    @GetMapping("OqcDataFiles")
    public ResponseEntity<?> getOqcDataFiles(@RequestParam String reqNo) {
        return new ResponseEntity<>(this.oqcCheckSvc.getOqcDataFiles(reqNo), HttpStatus.OK);
    }

    @PostMapping("DownloadOqcDataFile")
    public ResponseEntity<byte[]> downloadOqcDataFile(@RequestBody SearchVo searchVo) {

        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(searchVo.getUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);
    }

    @GetMapping("ConfigAudit")
    public ResponseEntity<?> getConfigAudit() {
        String configName = "oqc_check_audit";
        return new ResponseEntity<>(this.oqcCheckSvc.getConfigAudit(configName), HttpStatus.OK);
    }

    @GetMapping("ChangeConfigAudit")
    public ResponseEntity<?> changeConfigAudit(@RequestParam String configValue) {
        return new ResponseEntity<>(this.oqcCheckSvc.changeConfigAudit(configValue), HttpStatus.OK);
    }


}
