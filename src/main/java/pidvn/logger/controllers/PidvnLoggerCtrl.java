package pidvn.logger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidvn.commons.dto.ApiResponse;
import pidvn.logger.models.FileInfo;
import pidvn.logger.models.SearchDto;
import pidvn.logger.services.PidvnLoggerSvcImpl;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("Pidvn/Logger")
public class PidvnLoggerCtrl {

    @Autowired
    private PidvnLoggerSvcImpl pidvnLoggerSvc;

    @PostMapping("LogFiles")
    public ResponseEntity<ApiResponse<?>> LogFiles(@RequestBody SearchDto searchDto) throws IOException {
        ApiResponse<List<FileInfo>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(this.pidvnLoggerSvc.getLogFiles(searchDto.getPath()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("ViewLog")
    public ResponseEntity<String> readLogFileContent(@RequestBody SearchDto searchDto) {
        try {
            String content = this.pidvnLoggerSvc.readLogFileContent(searchDto);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Không thể đọc file: " + e.getMessage());
        }
    }



}
