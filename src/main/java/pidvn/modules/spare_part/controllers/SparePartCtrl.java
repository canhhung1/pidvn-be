package pidvn.modules.spare_part.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.SparePart;
import pidvn.entities.one.SparePartRecord;
import pidvn.entities.one.SparePartRequestDetail;
import pidvn.modules.spare_part.models.SearchVo;
import pidvn.modules.spare_part.services.SparePartSvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("SparePart")
public class SparePartCtrl {

    @Autowired
    private SparePartSvc sparePartSvc;

    @GetMapping("Users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.sparePartSvc.getUsers(), HttpStatus.OK);
    }

    /**
     * Lấy danh sách các Spare Part trong hệ thống
     * @return
     */
    @GetMapping("SpareParts")
    public ResponseEntity<?> getSparePart() {
        return new ResponseEntity<>(this.sparePartSvc.getSpareParts(), HttpStatus.OK);
    }

    /**
     * Thêm mới các mã Spare Part
     * @param sparePart
     * @return
     * @throws Exception
     */
    @PostMapping("SparePart")
    public ResponseEntity<?> saveSparePart(@RequestBody SparePart sparePart) throws Exception {
        return new ResponseEntity<>(this.sparePartSvc.saveSparePart(sparePart), HttpStatus.OK);
    }

    @PostMapping("GetSparePartRecords")
    public ResponseEntity<?> getSparePartRecords(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartRecords(searchVo), HttpStatus.OK);
    }

    /**
     * Lưu dữ liệu nhập xuất
     * @return
     */
    @PostMapping("SparePartRecords")
    public ResponseEntity<?> saveSparePartRecords(@RequestBody List<SparePartRecord> sparePartRecords) {
        return new ResponseEntity<>(this.sparePartSvc.saveSparePartRecords(sparePartRecords), HttpStatus.OK);
    }

    @PutMapping("SparePartRecord")
    public ResponseEntity<?> updateSparePartRecord(@RequestBody SparePartRecord sparePartRecord) {
        return new ResponseEntity<>(this.sparePartSvc.updateSparePartRecord(sparePartRecord), HttpStatus.OK);
    }

    @DeleteMapping("SparePartRecord/{id}")
    public ResponseEntity<?> deleteSparePartRecord(@PathVariable Integer id) {
        return new ResponseEntity<>(this.sparePartSvc.deleteSparePartRecord(id), HttpStatus.OK);
    }




    /**
     * Danh sách các line
     * @return
     */
    @GetMapping("Lines")
    public ResponseEntity<?> getLineStandard() {
        return new ResponseEntity<>(this.sparePartSvc.getLineStandard(), HttpStatus.OK);
    }

    /**
     * Danh sách các máy
     * @return
     */
    @GetMapping("Machines")
    public ResponseEntity<?> getMachineStandard() {
        return new ResponseEntity<>(this.sparePartSvc.getMachineStandard(), HttpStatus.OK);
    }




    @PostMapping("Charts")
    public ResponseEntity<?> getSparePartDataChart(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartDataChart(searchVo), HttpStatus.OK);
    }

    @PostMapping("SparePartRecordsByStandardPrice")
    public ResponseEntity<?> getSparePartRecordsByStandardPrice(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartRecordsByStandardPrice(searchVo), HttpStatus.OK);
    }


    /**
     * Các API liên quan đến tạo request
     */

    @PostMapping("CreateRequest")
    public ResponseEntity<?> createRequestSparePart(
            @RequestBody List<SparePartRequestDetail> spareParts,
            @RequestParam String factoryCode,
            @RequestParam Integer subsectionId
    ) {
        return new ResponseEntity<>(this.sparePartSvc.createRequestSparePart(spareParts, factoryCode, subsectionId), HttpStatus.OK);
    }

    /**
     * Lấy bộ phận
     * @return
     */
    @GetMapping("Sections")
    public ResponseEntity<?> getSections() {
        return new ResponseEntity<>(this.sparePartSvc.getSections(), HttpStatus.OK);
    }

    @GetMapping("Subsections")
    public ResponseEntity<?> getSubsection() {
        return new ResponseEntity<>(this.sparePartSvc.getSubsections(), HttpStatus.OK);
    }

    /**
     * Lấy danh sách
     * @return
     */
    @GetMapping("SparePartRequestMasters")
    public ResponseEntity<?> getSparePartRequestMaster() {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartRequestMaster(), HttpStatus.OK);
    }

    /**
     * Lấy thông tin chi tiết của request
     * @param requestId
     * @return
     */
    @GetMapping("SparePartRequestDetail/{requestId}")
    public ResponseEntity<?> getSparePartRequestDetailByRequestId(@PathVariable Integer requestId) {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartRequestDetailByRequestId(requestId), HttpStatus.OK);
    }

    @PostMapping("DownloadM4M8Request")
    public ResponseEntity downloadM4M8Request(@RequestParam Integer requestId) throws IOException {
        ByteArrayInputStream inputStream  = this.sparePartSvc.downloadQaCard(requestId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Export.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }
}
