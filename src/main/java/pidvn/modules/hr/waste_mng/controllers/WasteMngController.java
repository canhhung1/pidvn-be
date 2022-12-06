package pidvn.modules.hr.waste_mng.controllers;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.WasteMasterData;
import pidvn.modules.hr.waste_mng.models.WasteDataVo;
import pidvn.modules.hr.waste_mng.models.WasteSearchVo;
import pidvn.modules.hr.waste_mng.services.WasteMngService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("HR/WasteMng")
public class WasteMngController {

    @Autowired
    private WasteMngService wasteMngSvc;

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.wasteMngSvc.getUsers(), HttpStatus.OK);
    }

    @GetMapping("WasteGroup")
    public ResponseEntity<?> getWasteGroup() {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteGroup(), HttpStatus.OK);
    }

    @GetMapping("WasteType")
    public ResponseEntity<?> getWasteType(@RequestParam Integer wasteGroup) {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteType(wasteGroup), HttpStatus.OK);
    }

    @GetMapping("HandleCompany")
    public ResponseEntity<?> getHandleCompany() {
        return new ResponseEntity<>(this.wasteMngSvc.getHandleCompany(), HttpStatus.OK);
    }

    @PostMapping("CreateWasteMasterData")
    public ResponseEntity<?> createWasteMasterData(@RequestBody WasteMasterData masterData) {
        return new ResponseEntity<>(this.wasteMngSvc.createWasteMasterData(masterData), HttpStatus.OK);
    }

    @PostMapping("WasteMasterData")
    public ResponseEntity<?> getWasteMasterData(@RequestBody WasteSearchVo searchVo) {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteMasterData(searchVo), HttpStatus.OK);
    }

    @DeleteMapping("WasteMasterData/{id}")
    public ResponseEntity<?> removeWasteMasterData(@PathVariable Integer id) {
        this.wasteMngSvc.removeWasteMasterData(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("CreateWasteDetailData")
    public ResponseEntity<?> createWasteDetailData(@RequestBody WasteDataVo dataVo) {
        return new ResponseEntity<>(this.wasteMngSvc.createWasteDetailData(dataVo), HttpStatus.OK);
    }

    @PostMapping("WasteDetailData")
    public ResponseEntity<?> getWasteDetailData(@RequestBody WasteSearchVo searchVo) {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteDetailData(searchVo), HttpStatus.OK);
    }

    @DeleteMapping("WasteDetailData/{id}")
    public ResponseEntity<Void> removeWasteDetailData(@PathVariable Integer id) {
        // TODO
        this.wasteMngSvc.removeWasteDetailData(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("WasteDetailDataSummary")
    public ResponseEntity<?> getWasteDetailDataSummary(@RequestBody WasteSearchVo searchVo) {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteDetailDataSummary(searchVo), HttpStatus.OK);
    }

    @PostMapping("WasteDetailDataSummaryAll")
    public ResponseEntity<?> getWasteDetailDataSummaryAll(@RequestBody WasteSearchVo searchVo) {
        return new ResponseEntity<>(this.wasteMngSvc.getWasteDetailDataSummaryAll(searchVo), HttpStatus.OK);
    }

    @PostMapping("UploadImage")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.wasteMngSvc.uploadImage(file), HttpStatus.OK);
    }

    @DeleteMapping("RemoveImage/{id}")
    public ResponseEntity<?> removeImage(@PathVariable Integer id) throws Exception {
        Integer image = this.wasteMngSvc.removeImage(id);
        return new ResponseEntity<>(new HashMap<>().put("Response", "Removed image: " + image), HttpStatus.OK);
    }

    @GetMapping("ExportMasterData")
    public ResponseEntity<?> exportMasterData(@RequestParam Integer masterId) throws JRException {

        byte [] result = this.wasteMngSvc.exportMasterData(masterId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("ExportChungTu")
    public ResponseEntity<?> exportChungTu(@RequestParam Integer masterId) throws JRException {
        byte [] result = this.wasteMngSvc.exportChungTu(masterId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("SetupUnitPrice")
    public ResponseEntity<?> setupUnitPrice(@RequestParam Integer wasteMaster) {

        return new ResponseEntity<>(this.wasteMngSvc.setupUnitPrice(wasteMaster), HttpStatus.OK);
    }

    @PostMapping("ExcelDownload")
    public ResponseEntity<?> exportDetailExcel(@RequestBody WasteSearchVo searchVo) throws Exception {
        ByteArrayInputStream inputStream = this.wasteMngSvc.exportExcel(searchVo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Export.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }


}
