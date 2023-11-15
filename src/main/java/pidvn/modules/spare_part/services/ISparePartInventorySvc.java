package pidvn.modules.spare_part.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.util.List;
import java.util.Map;

public interface ISparePartInventorySvc  {

    List<SparePartRecordVo> getInventoryData(Integer requestId);
    Map uploadExcelInventoryData(MultipartFile file, Integer requestId);
}
