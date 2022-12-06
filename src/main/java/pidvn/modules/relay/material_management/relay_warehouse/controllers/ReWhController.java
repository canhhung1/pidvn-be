package pidvn.modules.relay.material_management.relay_warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.RelayWhRecords;
import pidvn.modules.relay.material_management.relay_warehouse.models.ReWhRecordSearchVo;
import pidvn.modules.relay.material_management.relay_warehouse.services.ReWhSvc;

import java.util.List;

@RestController
@RequestMapping("Relay/MaterialManagement/ReWh")
public class ReWhController {

    @Autowired
    private ReWhSvc reWhSvc;

    /**
     * Lấy list phiếu xuất kho
     * @return
     */
    @GetMapping("PXKs")
    public ResponseEntity<?> getListPXK() {
        return new ResponseEntity<>(this.reWhSvc.getListPXK(), HttpStatus.OK);
    }

    /**
     * Lấy các lots được xuất kho sang kho trung chuyển relay <br />
     * (Chi tiết phiếu xuất kho)
     * @param slipNo mã phiếu xuất kho
     * @return
     */
    @GetMapping("PXK/{slipNo}")
    public ResponseEntity<?> getLotsXuatKhoBySlipNo(@PathVariable String slipNo) {
        return new ResponseEntity<>(this.reWhSvc.getLotsXuatKhoBySlipNo(slipNo), HttpStatus.OK);
    }

    /**
     * Kho RE-WH nhận NVL từ PUR-WH
     * @param records danh sách các lots
     * @param slipNo mã phiếu xuất kho của Warehouse
     * @return
     */
    @PostMapping("ReceiveLotsFromWH")
    public ResponseEntity<?> receiveFromPurWhToReWh(@RequestBody List<RelayWhRecords> records, @RequestParam String slipNo) {
        return new ResponseEntity<>(this.reWhSvc.receiveLotsFromWH(records, slipNo), HttpStatus.OK);
    }

    @PostMapping("RelayWhRecords")
    public ResponseEntity<?> getRelayWhRecords(@RequestBody ReWhRecordSearchVo searchVo) {
        return new ResponseEntity<>(this.reWhSvc.getRelayWhRecords(searchVo), HttpStatus.OK);
    }

    /**
     * Kho RE-WH chuyển NVL vào LINE-WH
     * @param records danh sách các lots
     * @return
     */
    @PostMapping("SendMaterialToLineWh")
    public ResponseEntity<?> sendMaterialToLineWh(@RequestBody List<RelayWhRecords> records) {
        return new ResponseEntity<>(this.reWhSvc.sendMaterialToLineWh(records), HttpStatus.OK);
    }

}
