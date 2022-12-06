package pidvn.modules.relay.inventory.services;

import pidvn.entities.one.RelayInventory;
import pidvn.entities.one.RelayInventoryRequest;
import pidvn.modules.relay.inventory.models.BalanceVo;
import pidvn.modules.relay.inventory.models.InventoryRequestVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IRelayInventorySvc {

    List<InventoryRequestVo> getInventoryRequests();
    RelayInventoryRequest getInventoryRequest(Integer id);
    RelayInventoryRequest createInventoryRequest(RelayInventoryRequest request) throws ParseException;
    Map scanMaterial(RelayInventory materialVo);
    List<RelayInventory> getRelayInventories(String reqNo);
    RelayInventory saveRelayInventory(RelayInventory relayInventory);
    List<BalanceVo> getBalances(Integer reqId);
}
