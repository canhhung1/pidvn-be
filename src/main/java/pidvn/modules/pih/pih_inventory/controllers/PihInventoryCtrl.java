package pidvn.modules.pih.pih_inventory.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.modules.pih.pih_inventory.services.PihInventorySvc;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("PIH/Inventory")
public class PihInventoryCtrl {

    @Autowired
    private PihInventorySvc pihInventorySvc;

    /**
     * Lấy các khu vực kiểm kê PIH
     *
     * @return
     */
    @GetMapping("InventoryArea")
    private ResponseEntity<?> getInventoryArea() {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryArea(), HttpStatus.OK);
    }

    @GetMapping("Requests")
    public ResponseEntity<?> getInventoryRequests() {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryRequests(), HttpStatus.OK);
    }

    @GetMapping("Request/{requestId}")
    public ResponseEntity<?> getInventoryRequest(@PathVariable Integer requestId) {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryRequest(requestId), HttpStatus.OK);
    }

    @PostMapping("Request")
    public ResponseEntity<?> createInventoryRequest(@RequestBody PihInventoryRequest ivtReq) throws Exception {
        return new ResponseEntity<>(this.pihInventorySvc.createInventoryRequest(ivtReq), HttpStatus.OK);
    }

    @PostMapping("SaveListInventoryData")
    public ResponseEntity<?> saveListInventoryData(@RequestBody List<PihInventoryData> inventoryDataList,
                                                   @RequestParam Integer requestId,
                                                   @RequestParam Integer inventoryArea
    ) {
        return new ResponseEntity<>(this.pihInventorySvc.saveListInventoryData(inventoryDataList,requestId,inventoryArea), HttpStatus.OK);
    }

    @PostMapping("InventoryData")
    public ResponseEntity<?> saveInventoryData(@RequestBody PihInventoryData inventoryData) {
        return new ResponseEntity<>(this.pihInventorySvc.saveInventoryData(inventoryData), HttpStatus.OK);
    }

    @GetMapping("InventoryData")
    public ResponseEntity<?> getInventoryData(@RequestParam Integer requestId) {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryDataByRequestId(requestId), HttpStatus.OK);
    }

    @GetMapping("ScanLabel")
    public ResponseEntity<?> scanLabel(@RequestParam String lotNo) throws Exception {
        return new ResponseEntity<>(this.pihInventorySvc.scanLabel(lotNo), HttpStatus.OK);
    }

    /**
     * TODO
     * Chênh lệch thực tế lý thuyết
     *
     * @return
     */
    @GetMapping("Balance")
    public ResponseEntity<?> balance(
            @RequestParam Integer requestId, @RequestParam List<Integer> inventoryArea
    ) {
        return new ResponseEntity<>(this.pihInventorySvc.balance(requestId, inventoryArea), HttpStatus.OK);
    }


    /**
     * Kiêm kê NVL thô
     */

    @GetMapping("RawMaterialInventoryData")
    public ResponseEntity<?> getInventoryRawMaterialData(@RequestParam Integer requestId) {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryRawMaterialData(requestId), HttpStatus.OK);
    }

    @PostMapping("UploadRawMaterialInventoryData")
    public ResponseEntity<?> uploadRawMaterialInventoryData(
            @RequestBody MultipartFile file,
            @RequestParam Integer requestId
    ) throws IOException {
        return new ResponseEntity<>(this.pihInventorySvc.uploadRawMaterialInventoryData(file, requestId), HttpStatus.OK);
    }


    @PostMapping("DownloadTemplateUploadRawMaterialInventory")
    public ResponseEntity<byte[]> downloadTemplateUploadRawMaterialInventory() {
        byte[] file = new byte[0];
        String url = "P:\\IS\\(C) Save File FDCS\\FDCS-Server-2\\PIH\\PIH-Inventory\\Template_Upload_Raw_Material_Inventory_Data.xlsx";
        try {
            file = FileUtils.readFileToByteArray(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(file);
    }


}
