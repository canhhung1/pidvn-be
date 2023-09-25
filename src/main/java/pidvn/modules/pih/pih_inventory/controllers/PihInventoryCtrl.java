package pidvn.modules.pih.pih_inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.modules.pih.pih_inventory.services.PihInventorySvc;

import java.util.List;

@RestController
@RequestMapping("PIH/Inventory")
public class PihInventoryCtrl {

    @Autowired
    private PihInventorySvc pihInventorySvc;

    @GetMapping("Requests")
    public ResponseEntity<?> getInventoryRequests() {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryRequests(), HttpStatus.OK);
    }

    @PostMapping("Request")
    public ResponseEntity<?> createInventoryRequest(@RequestBody PihInventoryRequest ivtReq) throws Exception {
        return new ResponseEntity<>(this.pihInventorySvc.createInventoryRequest(ivtReq), HttpStatus.OK);
    }

    @PostMapping("SaveListInventoryData")
    public ResponseEntity<?> saveListInventoryData(@RequestBody List<PihInventoryData> inventoryDataList) {
        return new ResponseEntity<>(this.pihInventorySvc.saveListInventoryData(inventoryDataList), HttpStatus.OK);
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
    public ResponseEntity<?> scanLabel(@RequestParam String lotNo) {
        return new ResponseEntity<>(this.pihInventorySvc.scanLabel(lotNo), HttpStatus.OK);
    }

    /**
     * TODO
     * Chênh lệch thực tế lý thuyết
     * @return
     */
    @GetMapping("Balance")
    public ResponseEntity<?> balance(@RequestParam Integer requestId) {
        return new ResponseEntity<>(this.pihInventorySvc.balance(requestId), HttpStatus.OK);
    }


}
