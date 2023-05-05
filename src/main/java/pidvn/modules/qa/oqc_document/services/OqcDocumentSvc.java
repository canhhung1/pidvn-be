package pidvn.modules.qa.oqc_document.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.OqcDocument;
import pidvn.entities.one.OqcDocumentType;
import pidvn.mappers.one.qa.oqc_document.OqcDocumentMapper;
import pidvn.modules.qa.oqc_document.models.DocumentVo;
import pidvn.modules.qa.oqc_document.models.OqcDocumentVo;
import pidvn.modules.qa.oqc_document.models.ModelVo;
import pidvn.repositories.one.DefaultRepo;
import pidvn.repositories.one.OqcDocumentRepo;
import pidvn.repositories.one.OqcDocumentTypeRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OqcDocumentSvc implements IOqcDocumentSvc {

    private String ROOT_FOLDER = "P:\\IS\\CanhHung\\FDCS\\QA\\OQC\\Document";

    @Autowired
    private OqcDocumentMapper oqcDocumentMapper;

    @Autowired
    private OqcDocumentRepo oqcDocumentRepo;

    @Autowired
    private OqcDocumentTypeRepo oqcDocumentTypeRepo;

    @Autowired
    private DefaultRepo defaultRepo;

    @Override
    public List<ModelVo> getModels() {
        return this.oqcDocumentMapper.getModels();
    }

    @Override
    public Map uploadDocument(MultipartFile file, String model, Integer fileType, String createdBy) throws IOException {

        String fileName = file.getOriginalFilename();
        String url = this.ROOT_FOLDER + "\\" + model + "\\" + fileName;

        /**
         * Kiểm tra file đã tồn tại chưa
         */
        if (Files.exists(Paths.get(url))) {
            Map result = new HashMap();
            result.put("status", "ERROR");
            result.put("message", "File đã tồn tại");
            return result;
        }

        /**
         * Nếu file chưa tồn tại
         * Upload file vào folder
         */
        Path uploadPath = Paths.get(this.ROOT_FOLDER + "\\" + model);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        /**
         * Lưu vào bảng oqc_document
         */
        OqcDocument obj = new OqcDocument();
        obj.setModel(model);
        obj.setDocumentType(fileType);
        obj.setRootFolder(this.ROOT_FOLDER);
        obj.setFileName(fileName);
        obj.setFileFormat(file.getContentType());
        obj.setCreatedBy(createdBy);

        OqcDocument document = this.oqcDocumentRepo.save(obj);

        Map result = new HashMap();
        result.put("status", "OK");
        result.put("message", "Upload thành công");
        result.put("data", document);

        return result;
    }

    @Override
    public List<OqcDocumentVo> getOqcDocuments(String model) {
        return this.oqcDocumentMapper.getOqcDocuments(model);
    }

    @Override
    public List<OqcDocumentType> getOqcDocumentType() {
        return this.oqcDocumentTypeRepo.findAll();
    }

    @Override
    public Map deleteDocument(Integer fileId) throws IOException {

        OqcDocument file = this.oqcDocumentRepo.findById(fileId).get();
        String url = file.getRootFolder() + "\\" + file.getModel() + "\\" + file.getFileName();
        Path path = Paths.get(url);
        Files.delete(path);
        this.oqcDocumentRepo.deleteById(fileId);
        Map result = new HashMap();
        result.put("msg","Xóa thành công");
        result.put("status","OK");

        return result;
    }

    @Override
    public List<DocumentVo> getDocumentsPE(String model) {
        return this.oqcDocumentMapper.getDocumentsPE(model);
    }

    @Override
    public String createUrlDocumentPE(DocumentVo documentVo) {

        StringBuilder url = null;
        StringBuilder fileName = null;

        String docGroupName = documentVo.getDocGroupName();

        switch (docGroupName) {
            case "Relay General Spec":
            case "Relay Marking Drawing":
            case "Relay Packing Spec":
                fileName = new StringBuilder(documentVo.getIssueNo() + " " + documentVo.getTitle() + " " + documentVo.getVersion() + ".pdf");
                url = new StringBuilder(this.defaultRepo.findByName("server_link").getValue());
                url.append("\\Public Drive\\PE\\Specification\\RELAY\\" + documentVo.getDocTypeName() + "\\");
                url.append(fileName);
                break;
            case "Relay U drawing":
                String version = documentVo.getVersion() == null ? "" : documentVo.getVersion() + " ";
                fileName = new StringBuilder(documentVo.getIssueNo() + " " + version + "(" + documentVo.getModel() + ")" + ".pdf") ;
                url = new StringBuilder(this.defaultRepo.findByName("server_link").getValue());
                url.append("\\Public Drive\\PE\\Specification\\RELAY\\" + documentVo.getDocTypeName() + "\\");
                url.append(fileName);
                break;
            case "Relay Product Checksheet":
                fileName = new StringBuilder(documentVo.getModel() + " " + documentVo.getTitle() + ".pdf");
                url = new StringBuilder(this.defaultRepo.findByName("server_link").getValue());
                url.append("\\Public Drive\\PE\\Specification\\RELAY\\Product checksheet\\");
                url.append(fileName);
                break;
        }

        return url.toString();
    }
}
