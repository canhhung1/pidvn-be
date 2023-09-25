package pidvn.modules.pih.pih_inventory.services;

import pidvn.entities.one.Lots;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;

import java.util.List;
import java.util.Map;

public interface IPihInventorySvc {

    List<PihInventoryRequest> getInventoryRequests();

    PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception;

    Map saveListInventoryData(List<PihInventoryData> inventoryDataList);

    PihInventoryData saveInventoryData(PihInventoryData inventoryData);

    List<InventoryVo> getInventoryDataByRequestId(Integer requestId);

    Lots scanLabel(String lotNo);

    List<InventoryVo> balance(Integer requestId);
}
