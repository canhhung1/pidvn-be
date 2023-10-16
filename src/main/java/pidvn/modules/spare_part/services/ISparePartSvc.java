package pidvn.modules.spare_part.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISparePartSvc {

    List<Users> getUsers();

    List<SparePart> getSpareParts();

    SparePart saveSparePart(SparePart sparePart) throws Exception;

    List<SparePartRecordVo> getSparePartRecords();

//    SparePartRecord saveSparePartRecord(SparePartRecord sparePartRecord);

    List<SparePartRecord> saveSparePartRecords(List<SparePartRecord> sparePartRecords);

    SparePartInventoryRequest saveSparePartInventoryRequest(SparePartInventoryRequest request) throws Exception;

    List<SparePartInventoryRequest> getSparePartInventoryRequests();

    Map saveInventoryData(List<SparePartInventoryData> sparePartInventoryDataList);

    Map uploadExcel(MultipartFile file, String recordType) throws IOException;
}
