package pidvn.mappers.one.qa.oqc_document;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.qa.oqc_document.models.DocumentVo;
import pidvn.modules.qa.oqc_document.models.ModelVo;
import pidvn.modules.qa.oqc_document.models.OqcDocumentVo;

import java.util.List;

@Mapper
public interface OqcDocumentMapper {
    List<ModelVo> getModels();
    List<OqcDocumentVo> getOqcDocuments(String model);
    List<DocumentVo> getDocumentsPE(String model);
}
