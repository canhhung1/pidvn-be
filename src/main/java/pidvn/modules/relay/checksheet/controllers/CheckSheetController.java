package pidvn.modules.relay.checksheet.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidvn.modules.relay.checksheet.models.*;
import pidvn.modules.relay.checksheet.services.CheckSheetService;
import pidvn.modules.relay.checksheet.utils.CheckSheetExporter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("Relay/CheckSheet")
public class CheckSheetController {

    @Autowired
    private CheckSheetService checkSheetSvc;

    @PostMapping("Master")
    public ResponseEntity getCheckSheetMaster(@RequestBody MasterSearchVo searchVo) {
        return new ResponseEntity(this.checkSheetSvc.getCheckSheetMaster(searchVo), HttpStatus.OK);
    }

    @GetMapping("Processes")
    public ResponseEntity getProcesses(@RequestParam Integer master, @RequestParam String line, @RequestParam String model) {
        return new ResponseEntity(this.checkSheetSvc.getProcesses(master, line, model), HttpStatus.OK);
    }

    @GetMapping("Items")
    public ResponseEntity getItems(@RequestParam Integer master, @RequestParam Integer process) {
        return new ResponseEntity(this.checkSheetSvc.getItems(master, process), HttpStatus.OK);
    }

    @PostMapping("Item")
    public ResponseEntity addItem(@RequestBody ItemVo itemVo) {
        return new ResponseEntity(this.checkSheetSvc.addItem(itemVo), HttpStatus.OK);
    }

    // TODO: Đang làm dở
    @PutMapping("Item")
    public ResponseEntity editItem(@RequestBody ItemVo itemVo) {
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @PostMapping("ExportData")
    public ResponseEntity<?> exportData(@RequestBody MasterVo masterVo) throws IOException {

        List<DetailVo> detailsList = this.checkSheetSvc.getDetails(masterVo.getLine(), masterVo.getId());

        CheckSheetExporter exporter = new CheckSheetExporter(detailsList);

        ByteArrayInputStream inputStream = exporter.export();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=CheckSheetData.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @PutMapping("ApproveCheckSheet")
    public ResponseEntity<?> approveCheckSheet(@RequestBody MasterVo masterVo) {
        return new ResponseEntity<>(this.checkSheetSvc.approveCheckSheet(masterVo), HttpStatus.OK);
    }

    // Update detail
    @PutMapping("Detail")
    public ResponseEntity<?> editDetail(@RequestBody DetailVo detailVo) {
        return new ResponseEntity<>(this.checkSheetSvc.editDetail(detailVo), HttpStatus.OK);
    }

    @GetMapping(value = "GuideLines")
    public Object guideLines() {
        File file = new File("P:\\MA\\CanhHung\\CheckSheet\\CheckSheet-Guide.pdf");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("Models")
    public ResponseEntity<?> getModels() {
        return new ResponseEntity<>(this.checkSheetSvc.getModels(), HttpStatus.OK);
    }

    @PutMapping("Process")
    public ResponseEntity<?> updateProcess(@RequestBody ProcessVo processVo) {
        return new ResponseEntity<>(this.checkSheetSvc.updateProcess(processVo), HttpStatus.OK);
    }


}
