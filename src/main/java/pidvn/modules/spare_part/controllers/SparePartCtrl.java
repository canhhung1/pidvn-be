package pidvn.modules.spare_part.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pidvn.entities.one.SparePart;
import pidvn.entities.one.SparePartInventoryData;
import pidvn.entities.one.SparePartInventoryRequest;
import pidvn.entities.one.SparePartRecord;
import pidvn.exceptions.ConflictException;
import pidvn.modules.spare_part.services.SparePartSvc;

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

    @GetMapping("SpareParts")
    public ResponseEntity<?> getSparePart() {
        return new ResponseEntity<>(this.sparePartSvc.getSpareParts(), HttpStatus.OK);
    }

    @PostMapping("SparePart")
    public ResponseEntity<?> saveSparePart(@RequestBody SparePart sparePart) throws Exception {
        return new ResponseEntity<>(this.sparePartSvc.saveSparePart(sparePart), HttpStatus.OK);
    }

    @GetMapping("SparePartRecords")
    public ResponseEntity<?> getSparePartRecords() {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartRecords(), HttpStatus.OK);
    }

    /**
     * Lưu dữ liệu nhập xuất
     * @return
     */
    @PostMapping("SparePartRecords")
    public ResponseEntity<?> saveSparePartRecords(@RequestBody List<SparePartRecord> sparePartRecords) {
        return new ResponseEntity<>(this.sparePartSvc.saveSparePartRecords(sparePartRecords), HttpStatus.OK);
    }

    @GetMapping("SparePartInventoryRequests")
    public ResponseEntity<?> getSparePartInventoryRequests() {
        return new ResponseEntity<>(this.sparePartSvc.getSparePartInventoryRequests(), HttpStatus.OK);
    }

    @PostMapping("SparePartInventoryRequest")
    public ResponseEntity<?> saveSparePartInventoryRequest(@RequestBody SparePartInventoryRequest request) throws ResponseStatusException, ConflictException {
        return new ResponseEntity<>(this.sparePartSvc.saveSparePartInventoryRequest(request), HttpStatus.OK);
    }

    @PostMapping("SparePartInventoryData")
    public ResponseEntity<?> saveInventoryData(@RequestBody List<SparePartInventoryData> sparePartInventoryDataList) {
        return new ResponseEntity<>(this.sparePartSvc.saveInventoryData(sparePartInventoryDataList), HttpStatus.OK);
    }
    @PostMapping("UploadExcel")
    public ResponseEntity<?> uploadExcel(@RequestBody MultipartFile file, @RequestParam String recordType) throws IOException {
        return new ResponseEntity<>(this.sparePartSvc.uploadExcel(file, recordType), HttpStatus.OK);
    }
}
