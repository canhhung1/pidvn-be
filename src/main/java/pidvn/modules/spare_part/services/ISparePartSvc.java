package pidvn.modules.spare_part.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.spare_part.models.SearchVo;
import pidvn.modules.spare_part.models.SparePartRecordVo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISparePartSvc {

    List<Users> getUsers();

    List<SparePart> getSpareParts();

    SparePart saveSparePart(SparePart sparePart) throws Exception;

    List<SparePartRecordVo> getSparePartRecords(SearchVo searchVo);

//    SparePartRecord saveSparePartRecord(SparePartRecord sparePartRecord);

    List<SparePartRecord> saveSparePartRecords(List<SparePartRecord> sparePartRecords);

    SparePartRecord updateSparePartRecord(SparePartRecord sparePartRecord);

    Map deleteSparePartRecord(Integer id);

    SparePartInventoryRequest saveSparePartInventoryRequest(SparePartInventoryRequest request) throws Exception;

    List<SparePartInventoryRequest> getSparePartInventoryRequests();

    Map saveInventoryData(List<SparePartInventoryData> sparePartInventoryDataList);

    Map uploadExcel(MultipartFile file, String recordType) throws IOException;


    List<SparePartLineStandard> getLineStandard();
    List<SparePartMachineStandard> getMachineStandard();


    Map getSparePartDataChart(SearchVo searchVo);

    List<SparePartRecordVo> getSparePartRecordsByStandardPrice(SearchVo searchVo);


    /**
     * Các Api liên quan đến tạo request
     */

    List<SparePartRequestDetail> createRequestSparePart(List<SparePartRequestDetail> spareParts, Integer sectionId);

    List<Section> getSections();

    List<SparePartRequestMaster> getSparePartRequestMaster();

    List<SparePartRequestDetail> getSparePartRequestDetailByRequestId(Integer requestId);

    ByteArrayInputStream downloadQaCard(Integer requestId) throws IOException;
}
