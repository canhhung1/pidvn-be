package pidvn.modules.spare_part.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.spare_part.services.SparePartInventorySvc;

import java.io.IOException;

@RestController
@RequestMapping("SparePart/Inventory")
public class SparePartInventoryCtrl {

    @Autowired
    private SparePartInventorySvc sparePartInventorySvc;

    @GetMapping("Request/{requestId}")
    public ResponseEntity<?> getInventoryRequest(@PathVariable Integer requestId) {
        return new ResponseEntity<>(this.sparePartInventorySvc.getInventoryRequest(requestId), HttpStatus.OK);
    }

    @GetMapping("Data")
    public ResponseEntity<?> getInventoryData(@RequestParam Integer requestId) {
        return new ResponseEntity<>(this.sparePartInventorySvc.getInventoryData(requestId), HttpStatus.OK);
    }

    @PostMapping("UploadExcel")
    public ResponseEntity<?> uploadExcelInventoryData(@RequestBody MultipartFile file, @RequestParam Integer requestId) throws IOException {
        return new ResponseEntity<>(this.sparePartInventorySvc.uploadExcelInventoryData(file, requestId), HttpStatus.OK);
    }











}
