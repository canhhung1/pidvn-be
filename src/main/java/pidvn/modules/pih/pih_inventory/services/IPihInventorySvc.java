package pidvn.modules.pih.pih_inventory.services;

import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;

import java.util.List;
import java.util.Map;

public interface IPihInventorySvc {

    List<PihInventoryRequest> getInventoryRequests();

    PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception;

    Map saveInventoryData(List<PihInventoryData> inventoryData);

    List<PihInventoryData> getInventoryDataByRequestId(Integer requestId);
}
