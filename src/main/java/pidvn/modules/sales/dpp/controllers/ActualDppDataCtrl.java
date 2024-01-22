package pidvn.modules.sales.dpp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("Sales/ActualDppData")
public class ActualDppDataCtrl {

    @GetMapping("GetAndWriteExcelActualDpp")
    public ResponseEntity<?> getAndWriteActualDpp() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.92.176.57:8890/pidvn-schedule-task/ActualDppData/GetAndWriteActualDpp";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String data = response.getBody();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
