package pidvn.modules.spare_part.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.SparePartInventoryRequest;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.util.List;
import java.util.Map;

public interface ISparePartInventorySvc  {

    SparePartInventoryRequest getInventoryRequest(Integer requestId);

    List<SparePartRecordVo> getInventoryData(Integer requestId);
    Map uploadExcelInventoryData(MultipartFile file, Integer requestId);
}
