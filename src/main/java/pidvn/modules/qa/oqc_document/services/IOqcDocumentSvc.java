package pidvn.modules.qa.oqc_document.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.OqcDocumentType;
import pidvn.modules.qa.oqc_document.models.DocumentVo;
import pidvn.modules.qa.oqc_document.models.OqcDocumentVo;
import pidvn.modules.qa.oqc_document.models.ModelVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IOqcDocumentSvc {
    List<ModelVo> getModels();
    Map uploadDocument(MultipartFile file, String model, Integer fileType, String createdBy) throws IOException;
    List<OqcDocumentVo> getOqcDocuments(String model);
    List<OqcDocumentType> getOqcDocumentType();
    Map deleteDocument(Integer documentId) throws IOException;


    List<DocumentVo> getDocumentsPE(String model);
    String createUrlDocumentPE(DocumentVo documentVo);
}
