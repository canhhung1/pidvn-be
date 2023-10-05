package pidvn.modules.spare_part.services;

import pidvn.entities.one.*;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.util.List;
import java.util.Map;

public interface ISparePartSvc {

    List<Users> getUsers();

    List<SparePart> getSpareParts();

    SparePart saveSparePart(SparePart sparePart) throws Exception;

    List<SparePartRecordVo> getSparePartRecords();

    SparePartRecord saveSparePartRecord(SparePartRecord sparePartRecord);

    SparePartInventoryRequest saveSparePartInventoryRequest(SparePartInventoryRequest request) throws Exception;

    List<SparePartInventoryRequest> getSparePartInventoryRequests();

    Map saveInventoryData(List<SparePartInventoryData> sparePartInventoryDataList);
}
