package pidvn.mappers.one.relay.inventory;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.inventory.models.BalanceVo;
import pidvn.modules.relay.inventory.models.InventoryRequestVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface RelayInventoryMapper {
    List<InventoryRequestVo> getInventoryRequests();
    List<BalanceVo> getBalance(String reqNo, Date cutOff, Date ivtDate);
}
