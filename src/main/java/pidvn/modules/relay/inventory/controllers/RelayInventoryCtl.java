package pidvn.modules.relay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.RelayInventory;
import pidvn.entities.one.RelayInventoryRequest;
import pidvn.modules.relay.inventory.services.RelayInventorySvc;

@RestController
@RequestMapping("Relay/Inventory")
public class RelayInventoryCtl {

    @Autowired
    private RelayInventorySvc reIvtSvc;

    @GetMapping("Requests")
    public ResponseEntity<?> getInventoryRequests() {
        return new ResponseEntity<>(this.reIvtSvc.getInventoryRequests(), HttpStatus.OK);
    }

    @GetMapping("Request/{id}")
    public ResponseEntity<?> getInventoryRequests(@PathVariable Integer id) {
        return new ResponseEntity<>(this.reIvtSvc.getInventoryRequest(id), HttpStatus.OK);
    }

    @PostMapping("Request")
    public ResponseEntity<?> createInventoryRequest(@RequestBody RelayInventoryRequest request) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.getAuthorities().stream().anyMatch(a -> (a.getAuthority().equals("create_relay_inventory_request")) || (a.getAuthority().equals("super_admin")))) {
            throw new Exception("Bạn không có quyền !");
        }
        return new ResponseEntity<>(this.reIvtSvc.createInventoryRequest(request), HttpStatus.OK);
    }

    @GetMapping("RelayInventories")
    public ResponseEntity<?> getRelayInventories(@RequestParam String reqNo) {
        return new ResponseEntity<>(this.reIvtSvc.getRelayInventories(reqNo), HttpStatus.OK);
    }

    @PostMapping("RelayInventory")
    public ResponseEntity<?> saveRelayInventory(@RequestBody RelayInventory relayInventory) {
        return new ResponseEntity<>(this.reIvtSvc.saveRelayInventory(relayInventory), HttpStatus.OK);
    }

    @PostMapping("ScanMaterial")
    private ResponseEntity<?> scanMaterial(@RequestBody RelayInventory materialVo) {
        return new ResponseEntity<>(this.reIvtSvc.scanMaterial(materialVo), HttpStatus.OK);
    }

    @GetMapping("Balances")
    public ResponseEntity<?> getBalances(@RequestParam Integer reqId) {
        return new ResponseEntity<>(this.reIvtSvc.getBalances(reqId), HttpStatus.OK);
    }
}
