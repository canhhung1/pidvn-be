package pidvn.modules.warehouse.material.receipt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.warehouse.material.receipt.models.*;
import pidvn.modules.warehouse.material.receipt.services.MaterialReceiptService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Warehouse/Material/Receipt")
public class MaterialReceiptController {

    @Autowired
    private MaterialReceiptService materialReceiptService;

    @PostMapping("")
    public ResponseEntity<?> getMaterials(@RequestBody PurWhRecordsSearchVo searchVo) {

        // Get record total
        searchVo.setPaging(false);
        // searchVo.setLotNo(null);
        searchVo.setVendorCode(null);

        List<PurWhRecordsVo> result = this.materialReceiptService.getMaterials(searchVo);

        Double totalQty = result.stream().mapToDouble(p -> p.getQty()).sum();
        int recordTotal = result.size();

        searchVo.setPaging(true);

        Map<String, Object> response = new HashMap<>();
        response.put("recordTotal", recordTotal);
        response.put("totalQty", totalQty);
        response.put("data", this.materialReceiptService.getMaterials(searchVo));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("SaveMaterials")
    public ResponseEntity<?> saveMaterials(@RequestBody List<MaterialVo> materialVos) throws Exception {
        Map result = this.materialReceiptService.saveMaterials(materialVos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("GetLotsByLotNo")
    public ResponseEntity<?> getLotsByLotNo(@RequestBody LotSearchVo searchVo) {
        List<LotVo> result = this.materialReceiptService.getLotsByLotNo(searchVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Lưu dữ liệu thùng to
     * @param materialVos
     * @return
     */
    @PostMapping("SaveBigBox")
    public ResponseEntity<?> saveBigBox(@RequestBody List<MaterialVo> materialVos) throws Exception {
        Map result = this.materialReceiptService.saveBigBox(materialVos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("Invoices")
    public ResponseEntity<?> getInvoices(@RequestBody InvoiceSearchVo searchVo) {
        return new ResponseEntity<>(this.materialReceiptService.getInvoices(searchVo), HttpStatus.OK);
    }

    @PostMapping("InvoiceDetail")
    public ResponseEntity<?> getInvoiceDetail(@RequestBody InvoiceSearchVo searchVo) {
        return new ResponseEntity<>(this.materialReceiptService.getInvoiceDetail(searchVo), HttpStatus.OK);
    }

    @GetMapping("Slip")
    public ResponseEntity<?> findSlipByInvoice(@RequestParam String invoice) {
        return new ResponseEntity<>(this.materialReceiptService.findSlipByInvoice(invoice), HttpStatus.OK);
    }

    @PutMapping("Record")
    public ResponseEntity<?> deleteRecord(@RequestBody PurWhRecordsVo purWhRecordsVo) {
        this.materialReceiptService.deleteRecord(purWhRecordsVo);
        HashMap<String, String> result = new HashMap<>();
        result.put("result", "Deleted !");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("DetailOuterLabel")
    public ResponseEntity<?> getDetailOuterLabel(@RequestBody List<String> outerLabels) {
        return new ResponseEntity<>(this.materialReceiptService.getDetailOuterLabel(outerLabels), HttpStatus.OK);
    }

    @PostMapping("CreatePurWhHeader")
    public ResponseEntity<?> createPurWhHeader(@RequestBody PurWhHeaderVo purWhHeaderVo) {
        return new ResponseEntity<>(this.materialReceiptService.createPurWhHeader(purWhHeaderVo), HttpStatus.OK);
    }

}
