package pidvn.modules.qa.oqc_document.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.qa.oqc_document.models.DocumentVo;
import pidvn.modules.qa.oqc_document.models.OqcDocumentVo;
import pidvn.modules.qa.oqc_document.services.OqcDocumentSvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("QA/OqcDocument")
public class OqcDocumentCtrl {

    @Autowired
    private OqcDocumentSvc oqcDocumentSvc;

    @GetMapping("Models")
    public ResponseEntity<?> getModels() {
        return new ResponseEntity<>(this.oqcDocumentSvc.getModels(), HttpStatus.OK);
    }

    @PostMapping("Upload")
    public ResponseEntity<?> uploadDocument(
            @RequestBody MultipartFile file, @RequestParam String model, @RequestParam Integer fileType, @RequestParam String createdBy
    ) throws IOException {

        Map result = this.oqcDocumentSvc.uploadDocument(file, model, fileType, createdBy);

        if (result.get("status").equals("ERROR")) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("Documents")
    public ResponseEntity<?> getDocuments(@RequestParam @Nullable String model) {
        return new ResponseEntity<>(this.oqcDocumentSvc.getOqcDocuments(model), HttpStatus.OK);
    }

    @DeleteMapping("Document/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Integer id) throws IOException {
        return new ResponseEntity<>(this.oqcDocumentSvc.deleteDocument(id), HttpStatus.OK);
    }

    @GetMapping("DocumentTypes")
    public ResponseEntity<?> getOqcDocumentTypes() {
        return new ResponseEntity<>(this.oqcDocumentSvc.getOqcDocumentType(), HttpStatus.OK);
    }

    @PostMapping(value = "Preview")
    public Object previewFile(@RequestBody OqcDocumentVo oqcDocumentVo) {

        String url = oqcDocumentVo.getRootFolder() + "\\" + oqcDocumentVo.getModel() + "\\" + oqcDocumentVo.getFileName();

        File file = new File(url);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("Download")
    public ResponseEntity<byte[]> downloadDocument(@RequestBody OqcDocumentVo oqcDocumentVo) {
        String url = oqcDocumentVo.getRootFolder() + "\\" + oqcDocumentVo.getModel() + "\\" + oqcDocumentVo.getFileName();
        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);
    }


    /**
     * Lấy các tài liệu PE phát hành
     * @param model
     * @return
     */
    @GetMapping("DocumentsPE")
    public ResponseEntity<?> getDocumentsPE(@RequestParam String model) {
        return new ResponseEntity<>(this.oqcDocumentSvc.getDocumentsPE(model), HttpStatus.OK);
    }

    /**
     * Xem document PE phát hành
     * @param documentVo
     * @return
     */
    @PostMapping(value = "PreviewDocumentPE")
    public ResponseEntity<?> previewDocumentPE(@RequestBody DocumentVo documentVo) {

        String url = this.oqcDocumentSvc.createUrlDocumentPE(documentVo);

        byte[] file = new byte[0];

        try {
            file = FileUtils.readFileToByteArray(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(file);

    }
}