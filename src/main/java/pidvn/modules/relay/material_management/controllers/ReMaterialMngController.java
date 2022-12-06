package pidvn.modules.relay.material_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.InspectQaCard;
import pidvn.entities.one.PurWhRecords;
import pidvn.modules.relay.material_management.models.PurWhRecordSearchVo;
import pidvn.modules.relay.material_management.models.PurWhRecordVo;
import pidvn.modules.relay.material_management.models.SearchVo;
import pidvn.modules.relay.material_management.services.ReMaterialMngSvc;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("Relay/MaterialManagement")
public class ReMaterialMngController {

    @Autowired
    private ReMaterialMngSvc reMaterialMngSvc;

    @GetMapping("Lines")
    public ResponseEntity<?> getLines(@RequestParam Integer productId) {
        return new ResponseEntity<>(this.reMaterialMngSvc.getLines(productId), HttpStatus.OK);
    }

    /**
     * Lấy ra các WIP trên line
     * @return
     */
    @GetMapping("Processes")
    public ResponseEntity<?> getProcesses() {
        return new ResponseEntity<>(this.reMaterialMngSvc.getProcesses(5), HttpStatus.OK);
    }

    /**
     * Kiểm tra Model được tạo ra bởi những NVL nào
     * @param model
     * @return
     */
    @GetMapping("Parts")
    public ResponseEntity<?> getPartsByModel(@RequestParam String model) {
        return new ResponseEntity<>(this.reMaterialMngSvc.getPartsByModel(model), HttpStatus.OK);
    }

    /**
     * Lấy các lots được xuất kho sang kho trung chuyển relay <br />
     * (Chi tiết phiếu xuất kho)
     * @param slipNo mã phiếu xuất kho
     * @return
     */
    @GetMapping("PXK")
    public ResponseEntity<?> getLotsBySlipNo(@RequestParam String slipNo) {
        return new ResponseEntity<>(this.reMaterialMngSvc.getLotsBySlipNo(slipNo), HttpStatus.OK);
    }

    @PostMapping("PurWhRecords")
    public ResponseEntity<?> getPurWhRecords(@RequestBody PurWhRecordSearchVo searchVo) {
        return new ResponseEntity<>(this.reMaterialMngSvc.getPurWhRecords(searchVo), HttpStatus.OK);
    }

    /**
     * Xóa PurWhRecord
     * @param id
     * @return
     */
    @DeleteMapping("PurWhRecords/{id}")
    public ResponseEntity<?> deletePurWhRecord(@PathVariable Integer id) {
        return new ResponseEntity<>(this.reMaterialMngSvc.deletePurWhRecord(id), HttpStatus.OK);
    }

    /**
     * Scan nguyên vật liệu
     * @param record
     * @return
     */
    @PostMapping("ScanMaterial")
    public ResponseEntity<?> scanMaterial(@RequestBody PurWhRecordVo record) {
        return new ResponseEntity<>(this.reMaterialMngSvc.scanMaterial(record), HttpStatus.OK);
    }

    @PostMapping("SavePurWhRecords")
    public ResponseEntity<?> savePurWhRecords(@RequestBody List<PurWhRecords> purWhRecords) {
        return new ResponseEntity<>(this.reMaterialMngSvc.savePurWhRecords(purWhRecords), HttpStatus.OK);
    }

    @PostMapping("ChangeQaCard")
    public ResponseEntity<?> changeQaCard(@RequestParam String oldQaCard, @RequestParam String newQaCard) throws ParseException {
        return new ResponseEntity<>(this.reMaterialMngSvc.changeQaCard(oldQaCard, newQaCard), HttpStatus.OK);
    }

    @PostMapping("InspectQACard")
    public ResponseEntity<?> saveInspectQACard(@RequestBody InspectQaCard inspectQaCard) {
        return new ResponseEntity<>(this.reMaterialMngSvc.saveInspectQACard(inspectQaCard), HttpStatus.OK);
    }

    @GetMapping("InspectQACards/{inspectQaCard}")
    public ResponseEntity<?> getInspectQaCards(@PathVariable String inspectQaCard) {
        return new ResponseEntity<>(this.reMaterialMngSvc.getInspectQaCards(inspectQaCard), HttpStatus.OK);
    }

    @DeleteMapping("InspectQACards/{inspectQaCard}")
    public ResponseEntity<?> deleteInspectQaCard(@PathVariable Integer inspectQaCard) {
        return new ResponseEntity<>(this.reMaterialMngSvc.deleteInspectQaCard(inspectQaCard), HttpStatus.OK);
    }

    @PostMapping("Traceability")
    public ResponseEntity<?> materialTraceability(@RequestBody SearchVo searchVo) {
        return new ResponseEntity<>(this.reMaterialMngSvc.materialTraceability(searchVo), HttpStatus.OK);
    }

}
