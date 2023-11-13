package pidvn.modules.spare_part.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ISparePartInventorySvc  {


    Map uploadExcelInventoryData(MultipartFile file);
}
