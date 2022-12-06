package pidvn.modules.warehouse.iqc.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.warehouse.iqc.models.InvoiceSrchVo;
import pidvn.modules.warehouse.iqc.models.IqcRequestSearchVo;
import pidvn.modules.warehouse.iqc.models.IqcRequestVo;
import pidvn.modules.warehouse.iqc.services.WhIqcService;

import java.util.HashMap;

@RestController
@RequestMapping("WH/IQC")
public class WhIqcController {

    @Autowired
    private WhIqcService whIqcSvc;

    @GetMapping("Invoices")
    public ResponseEntity<?> getInvoices() {
        return new ResponseEntity<>(this.whIqcSvc.getInvoices(), HttpStatus.OK);
    }

    @PostMapping("Requests")
    public ResponseEntity<?> getIqcRequests(@RequestBody IqcRequestSearchVo searchVo) {
        return new ResponseEntity<>(this.whIqcSvc.getIqcRequests(searchVo), HttpStatus.OK);
    }

    @PostMapping("Requests/{requestNo}")
    public ResponseEntity<?> getIqcRequestDetail(@PathVariable String requestNo, @RequestBody IqcRequestSearchVo searchVo) {
        searchVo.setRequestNo(requestNo);
        return new ResponseEntity<>(this.whIqcSvc.getIqcRequestDetail(searchVo), HttpStatus.OK);
    }

    @PostMapping("Request")
    public ResponseEntity<?> createIqcRequest(@RequestBody IqcRequestVo iqcRequestVo) throws Exception {
        return new ResponseEntity<>(this.whIqcSvc.createIqcRequest(iqcRequestVo), HttpStatus.OK);
    }

    @GetMapping("SlipNo")
    public ResponseEntity<?> getSlipNoByInvoice(@RequestParam String invoice) {
        return new ResponseEntity<>(this.whIqcSvc.getSlipNoByInvoice(invoice), HttpStatus.OK);
    }


}
