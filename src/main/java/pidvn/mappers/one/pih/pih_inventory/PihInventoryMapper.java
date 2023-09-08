package pidvn.mappers.one.pih.pih_inventory;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.PihInventoryData;

import java.util.List;

@Mapper
public interface PihInventoryMapper {
    List<PihInventoryData> getInventoryData(Integer requestId);

}
