package pidvn.modules.spare_part.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("SparePart/Inventory")
public class SparePartInventoryCtrl {


    @PostMapping("uploadExcelInventoryData")
    public ResponseEntity<?> uploadExcelInventoryData(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }











}
