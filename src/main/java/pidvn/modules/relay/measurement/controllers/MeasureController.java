package pidvn.modules.relay.measurement.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.relay.measurement.models.*;
import pidvn.modules.relay.measurement.services.MeasureService;
import pidvn.modules.relay.measurement.utils.MeasureExporter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("Relay/Measurement")
public class MeasureController {

    @Autowired
    private MeasureService measureService;

    @GetMapping("Items")
    public ResponseEntity<?> getItems() {
        return new ResponseEntity<>(this.measureService.getItems(), HttpStatus.OK);
    }

    @GetMapping("Items/{id}")
    public ResponseEntity<?> getItems(@PathVariable Integer id) {
        return new ResponseEntity<>(this.measureService.getItemById(id), HttpStatus.OK);
    }

    @GetMapping("Standard")
    public ResponseEntity<?> getStandard(@RequestParam Integer itemId, @RequestParam String modelType) {
        MeaStandard standard = this.measureService.getStandard(itemId, modelType).get(0);
        Optional<MeaItem> item = this.measureService.getItems(itemId);
        Map result = new HashMap();
        result.put("item", item);
        result.put("standard", standard);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("Images")
    public ResponseEntity<?> getItemImages(@RequestParam Integer item) {
        List<MeaItemImage> result = this.measureService.getItemImages(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("ImagesV2")
    public ResponseEntity<?> getItemImages(@RequestParam Integer item, @RequestParam String modelType) {
        List<MeaItemImage> result = this.measureService.getItemImages(item, modelType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("UploadImage")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file, @RequestParam Integer item, @RequestParam String modelType) throws IOException {
        return new ResponseEntity<>(this.measureService.uploadImage(file, item,modelType), HttpStatus.OK);
    }

    @DeleteMapping("DeleteImage")
    public ResponseEntity<?> removeImage(@RequestParam Integer id) {
        this.measureService.removeImage(id);
        Map result = new HashMap();
        result.put("response", "Delete success Id: " + id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("SaveMeasureData")
    public ResponseEntity<?> saveMeasureData(@RequestBody List<MeasureDataVo> dataVoList) {
        List<MeasureDataVo> result = this.measureService.saveMeasureData(dataVoList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("MasterData")
    public ResponseEntity<?> getMasterData(@RequestBody MeasureSearchVo searchVo) {
        List<MeasureMasterDataVo> result = this.measureService.getMasterData(searchVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("DetailData")
    public ResponseEntity<?> getDetailData(@RequestBody MeasureSearchVo searchVo) {
        List<MeasureDetailDataVo> result = this.measureService.getDetailData(searchVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("ChildItems")
    public ResponseEntity<?> getChildItems(@RequestParam Integer item, @RequestParam String modelType) {
        return new ResponseEntity<>(this.measureService.getChildItems(item, modelType), HttpStatus.OK);
    }

    @GetMapping("ApproveData")
    public ResponseEntity<?> approveData(@RequestParam Integer master, @RequestParam String username) {
        return new ResponseEntity<>(this.measureService.approveData(master, username), HttpStatus.OK);
    }

    @PostMapping("ApproveData")
    public ResponseEntity<?> quickApprove(@RequestBody List<MeaMasterData> masterDataList, @RequestParam String approver) {
        return new ResponseEntity<>(this.measureService.quickApprove(masterDataList, approver), HttpStatus.OK);
    }

    @PostMapping("UpdateDetailData")
    public ResponseEntity<?> updateDetailData(@RequestBody List<MeaDetailData> detailDataList) {
        return new ResponseEntity<>(this.measureService.updateDetailData(detailDataList), HttpStatus.OK);
    }

    @PostMapping("ExportData")
    public ResponseEntity<?> exportData(@RequestBody ExportParams exportParams) throws IOException {

        MeasureSearchVo searchVo = new MeasureSearchVo();
        searchVo.setItem(exportParams.getItem());
        searchVo.setLine(exportParams.getLine());
        searchVo.setModel(exportParams.getModel());
        searchVo.setModelType(exportParams.getModelType());
        searchVo.setReason(exportParams.getReason());
        searchVo.setUser(exportParams.getUser());
        searchVo.setShift(exportParams.getShift());
        searchVo.setFromDate(exportParams.getFromDate());
        searchVo.setToDate(exportParams.getToDate());

        List<MeasureDetailDataVo> data = this.measureService.getDetailData(searchVo);

        MeasureExporter exporter = new MeasureExporter(data, exportParams);

        ByteArrayInputStream inputStream = exporter.export();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=MeasurementData.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @GetMapping(value = "GuideLines")
    public Object guideLines() {
        File file = new File("P:\\MA\\CanhHung\\Measurement\\Measurement-Guide.pdf");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
