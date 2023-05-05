package pidvn.modules.qa.oqc_check.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.OqcDataFile;
import pidvn.entities.one.OqcRequest;
import pidvn.modules.qa.oqc_check.models.OqcDataVo;
import pidvn.modules.qa.oqc_check.models.OqcRequestVo;
import pidvn.modules.qa.oqc_check.models.SearchVo;

import java.io.IOException;
import java.util.List;

public interface IOqcCheckSvc {
    List<OqcDataVo> getOqcMasterData(String reqNo, String qaCard);
    List<OqcRequestVo> getOqcRequests(SearchVo searchVo);
    OqcRequest updateOqcRequest(OqcRequestVo oqcRequestVo);
    OqcRequest oqcRequestHandle(OqcRequestVo oqcRequestVo);
    OqcDataFile uploadFile(MultipartFile file, String createdBy, String filePathRoot, String reqNo) throws IOException;
    List<OqcDataFile> getOqcDataFiles(String reqNo);

}
