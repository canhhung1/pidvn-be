package pidvn.modules.pih.pih_inventory.services;

import pidvn.entities.one.Lots;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.entities.one.ProductType;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPihInventorySvc {

    List<ProductType> getInventoryArea();

    List<PihInventoryRequest> getInventoryRequests();

    PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception;

    Map saveListInventoryData(List<PihInventoryData> inventoryDataList);

    PihInventoryData saveInventoryData(PihInventoryData inventoryData);

    List<InventoryVo> getInventoryDataByRequestId(Integer requestId);

    Lots scanLabel(String lotNo);

    List<InventoryVo> balance(Integer requestId, List<Integer> inventoryArea);

    Optional<PihInventoryRequest> getInventoryRequest(Integer requestId);
}
