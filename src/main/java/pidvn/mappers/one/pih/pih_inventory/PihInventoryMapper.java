package pidvn.mappers.one.pih.pih_inventory;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.PihInventoryData;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface PihInventoryMapper {
    List<InventoryVo> getInventoryData(Integer requestId);
    List<InventoryVo> balance(Integer requestId, Date fromDate, Date toDate, Integer requestIdKyTruoc, List<Integer> inventoryArea);



}
