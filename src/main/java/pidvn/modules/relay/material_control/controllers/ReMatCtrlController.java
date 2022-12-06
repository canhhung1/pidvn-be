package pidvn.modules.relay.material_control.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.PurWhRecords;
import pidvn.modules.relay.material_control.models.MaterialSearchVo;
import pidvn.modules.relay.material_control.models.MaterialVo;
import pidvn.modules.relay.material_control.services.ReMatCtrlSvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Relay/MaterialControl")
public class ReMatCtrlController {

    @Autowired
    private ReMatCtrlSvc reMatCtrlSvc;

    @GetMapping("Lines")
    public ResponseEntity<?> getLines(@RequestParam Integer productId) {
        return new ResponseEntity<>(this.reMatCtrlSvc.getLines(productId), HttpStatus.OK);
    }

    /**
     * Lấy ra các WIP trên line
     * @return
     */
    @GetMapping("Processes")
    public ResponseEntity<?> getProcesses() {
        return new ResponseEntity<>(this.reMatCtrlSvc.getProcesses(5), HttpStatus.OK);
    }

    /**
     * Kiểm tra Model được tạo ra bởi những NVL nào
     * @param model
     * @return
     */
    @GetMapping("Parts")
    public ResponseEntity<?> getPartsByModel(@RequestParam String model) {
        return new ResponseEntity<>(this.reMatCtrlSvc.getPartsByModel(model), HttpStatus.OK);
    }

    @GetMapping("Slips")
    public ResponseEntity<?> getSlips() {
        return new ResponseEntity<>(this.reMatCtrlSvc.getSlips(), HttpStatus.OK);
    }

    /**
     * Lấy các lots được xuất kho sang kho trung chuyển relay <br />
     * (Chi tiết phiếu xuất kho)
     * @param slipNo mã phiếu xuất kho
     * @return
     */
    @GetMapping("Slips/{slipNo}")
    public ResponseEntity<?> getMaterialsBySlipNo(@PathVariable String slipNo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.getMaterialsBySlipNo(slipNo), HttpStatus.OK);
    }

    @GetMapping("SlipsRelayReturnWarehouse")
    public ResponseEntity<?> getSlipsRelayReturnWarehouse() {
        return new ResponseEntity<>(this.reMatCtrlSvc.getSlipsRelayReturnWarehouse(), HttpStatus.OK);
    }

    @GetMapping("SlipsRelayReturnWarehouse/{slipNo}")
    public ResponseEntity<?> getSlipRelayReturnWarehouseDetail(@PathVariable String slipNo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.getSlipRelayReturnWarehouseDetail(slipNo), HttpStatus.OK);
    }

    @PostMapping("ScanMaterial")
    public ResponseEntity<?> scanMaterial(@RequestBody MaterialVo materialVo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.scanMaterial(materialVo), HttpStatus.OK);
    }

    @PostMapping("SaveMaterials")
    public ResponseEntity<?> saveMaterials(@RequestBody List<MaterialVo> materialVos) {
        return new ResponseEntity<>(this.reMatCtrlSvc.saveMaterials(materialVos), HttpStatus.OK);
    }

    @PostMapping("Materials")
    public ResponseEntity getMaterials(@RequestBody MaterialSearchVo searchVo) {
        return new ResponseEntity(this.reMatCtrlSvc.getMaterials(searchVo), HttpStatus.OK);
    }

    @PostMapping("ChangeQaCard")
    public ResponseEntity changeQACard(@RequestParam String oldQaCard, @RequestParam String newQaCard) throws ParseException {
        return new ResponseEntity(this.reMatCtrlSvc.changeQaCard(oldQaCard, newQaCard), HttpStatus.OK);
    }

    @PostMapping("Traceability")
    public ResponseEntity materialTraceability(@RequestBody MaterialSearchVo searchVo) {
        return new ResponseEntity(this.reMatCtrlSvc.materialTraceability(searchVo), HttpStatus.OK);
    }

    @PostMapping("MaterialsExport")
    public ResponseEntity exportMaterials(@RequestBody MaterialSearchVo searchVo) throws IOException {

        ByteArrayInputStream inputStream = this.reMatCtrlSvc.exportMaterialData(searchVo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Export.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @PostMapping("DeleteMaterial")
    public ResponseEntity<?> deleteMaterial(@RequestBody MaterialVo materialVo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.deleteMaterial(materialVo), HttpStatus.OK);
    }

    @PostMapping("EditMaterialControl")
    public ResponseEntity<?> editMaterialControl(@RequestBody MaterialVo materialVo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.editMaterialControl(materialVo), HttpStatus.OK);
    }

    @PostMapping("SavePurWhRecord")
    public ResponseEntity<?> savePurWhRecord(@RequestBody PurWhRecords purWhRecord) {
        return new ResponseEntity<>(this.reMatCtrlSvc.savePurWhRecords(purWhRecord), HttpStatus.OK);
    }

    @GetMapping("GenerateSlipNo")
    public ResponseEntity<?> generateSlipNo(@RequestParam String recordType, @RequestParam String username) throws ParseException {
        String slipNo = this.reMatCtrlSvc.generateSlipNo(recordType,username);
        Map result = new HashMap();
        result.put("slipNo",slipNo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Mapping QA Card

    @PostMapping("MappingQaCard")
    public ResponseEntity<?> mappingQaCard(@RequestBody MaterialControls materialControl) throws ParseException {
        return new ResponseEntity<>(this.reMatCtrlSvc.mappingQaCard(materialControl), HttpStatus.OK);
    }

    @GetMapping("ChildQaCards")
    public ResponseEntity<?> getChildQaCards(@RequestParam String parentQaCard) {
        return new ResponseEntity<>(this.reMatCtrlSvc.getChildQaCards(parentQaCard), HttpStatus.OK);
    }

    @DeleteMapping("ChildQaCards/{id}")
    public ResponseEntity<?> deleteChildQaCard(@PathVariable Integer id) {
        return new ResponseEntity<>(this.reMatCtrlSvc.deleteChildQaCard(id), HttpStatus.OK);
    }

    @GetMapping("QaCards")
    public ResponseEntity<?> getQaCard() {
        return new ResponseEntity<>(this.reMatCtrlSvc.getQaCard(), HttpStatus.OK);
    }

    /**
     * Sửa lại số lượng các NVL đã được nhập vào LINE
     * @param materialVo
     * @return
     */
    @PostMapping("EditQtyImportedToLine")
    public ResponseEntity<?> editQtyImportedToLine(@RequestBody MaterialVo materialVo) {
        return new ResponseEntity<>(this.reMatCtrlSvc.editQtyImportedToLine(materialVo), HttpStatus.OK);
    }


    @DeleteMapping("DeletePurWhRecord/{id}")
    public ResponseEntity<?> deletePurWhRecordById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.reMatCtrlSvc.deletePurWhRecordById(id), HttpStatus.OK);
    }
}
