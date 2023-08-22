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

    @PostMapping("InventoryData")
    public ResponseEntity<?> saveInventoryData(@RequestBody List<PihInventoryData> inventoryData) {
        return new ResponseEntity<>(this.pihInventorySvc.saveInventoryData(inventoryData), HttpStatus.OK);
    }

    @GetMapping("InventoryData")
    public ResponseEntity<?> getInventoryData(@RequestParam Integer requestId) {
        return new ResponseEntity<>(this.pihInventorySvc.getInventoryDataByRequestId(requestId), HttpStatus.OK);
    }

}
