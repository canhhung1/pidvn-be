package pidvn.modules.qa.iqc_check.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.qa.iqc_check.models.IqcDataSearchVo;
import pidvn.modules.qa.iqc_check.models.IqcDataVo;
import pidvn.modules.qa.iqc_check.models.IqcRequestSearchVo;
import pidvn.modules.qa.iqc_check.models.IqcRequestVo;
import pidvn.modules.qa.iqc_check.services.IqcCheckService;
import pidvn.modules.qa.iqc_check.utils.IqcCheckExporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("QA/IqcCheck")
public class IqcCheckController {

    @Autowired
    private IqcCheckService iqcCheckSvc;

    @PostMapping("Requests")
    public ResponseEntity<?> getIqcRequests(@RequestBody IqcRequestSearchVo searchVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.getIqcRequests(searchVo), HttpStatus.OK);
    }

    @PostMapping("IqcDataMaster")
    public ResponseEntity<?> getIqcDataMaster(@RequestBody IqcDataSearchVo searchVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.getIqcDataMaster(searchVo), HttpStatus.OK);
    }

    @PostMapping("IqcDataDetail")
    public ResponseEntity<?> getIqcDataDetail(@RequestBody IqcDataSearchVo searchVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.getIqcDataDetail(searchVo), HttpStatus.OK);
    }

    @PostMapping("SaveIqcDataMaster")
    public ResponseEntity<?> saveIqcDataMaster(@RequestBody IqcDataVo iqcDataVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.saveIqcDataMaster(iqcDataVo), HttpStatus.OK);
    }

    @PostMapping("SaveIqcDataDetail")
    public ResponseEntity<?> saveIqcDataDetail(@RequestBody IqcDataVo iqcDataVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.saveIqcDataDetail(iqcDataVo), HttpStatus.OK);
    }

    @DeleteMapping("DeleteIqcDataDetail")
    public ResponseEntity<?> deleteIqcDataDetail(@RequestParam Integer id) {
        HashMap map = new HashMap();
        map.put("result", "Đã xóa dữ liệu");
        this.iqcCheckSvc.deleteIqcDataDetail(id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("DeleteIqcDataMaster")
    public ResponseEntity<?> deleteIqcDataMaster(@RequestParam Integer id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("UpdateIqcRequest")
    public ResponseEntity<?> updateIqcRequest(@RequestParam String requestNo, @RequestParam Integer status) throws Exception {
        return new ResponseEntity<>(this.iqcCheckSvc.updateIqcRequest(requestNo, status), HttpStatus.OK);
    }

    @GetMapping("ExportExcel")
    public ResponseEntity<?> exportExcel(@RequestParam String requestNo, @RequestParam String invoice) throws IOException {

        IqcDataSearchVo searchVo = new IqcDataSearchVo();
        searchVo.setRequestNo(requestNo);
        searchVo.setInvoice(invoice);

        List<IqcDataVo> data = this.iqcCheckSvc.getIqcDataMaster(searchVo);

        IqcCheckExporter exporter = new IqcCheckExporter(data);
        ByteArrayInputStream inputStream = exporter.export();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=IqcData.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @GetMapping("ConfigAudit")
    public ResponseEntity<?> getConfigAudit() {
        String configName = "iqc_check_audit";
        return new ResponseEntity<>(this.iqcCheckSvc.getConfigAudit(configName), HttpStatus.OK);
    }

    @GetMapping("ChangeConfigAudit")
    public ResponseEntity<?> changeConfigAudit(@RequestParam String configValue) {
        return new ResponseEntity<>(this.iqcCheckSvc.changeConfigAudit(configValue), HttpStatus.OK);
    }

    /**
     *
     */





    @GetMapping("IqcDataSortingMaster/{requestNo}")
    public ResponseEntity<?> getIqcDataSortingMaster(@PathVariable String requestNo) {
        return new ResponseEntity<>(this.iqcCheckSvc.getIqcDataSortingMaster(requestNo), HttpStatus.OK);
    }


    @GetMapping("IqcDataSortingDetail/{requestNo}")
    public ResponseEntity<?> getIqcDataSortingDetail(@PathVariable String requestNo) {
        return new ResponseEntity<>(this.iqcCheckSvc.getIqcDataSortingDetail(requestNo), HttpStatus.OK);
    }

    @PostMapping("SaveIqcDataSortingDetail")
    public ResponseEntity<?> saveIqcDataSortingDetail(@RequestBody IqcDataVo iqcDataVo) {
        return new ResponseEntity<>(this.iqcCheckSvc.saveIqcDataSortingDetail(iqcDataVo), HttpStatus.OK);
    }
}
