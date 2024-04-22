package pidvn.mappers.one.pih.pih_inventory;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.PihInventoryData;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;

import java.util.Date;
import java.util.List;

@Mapper
public interface PihInventoryMapper {
    List<InventoryVo> getInventoryData(Integer requestId, Date fromDate, Date toDate);

    /**
     *
     * @param requestId
     * @param fromDate ngày tính data theo lý thuyết
     * @param toDate ngày kiểm kê
     * @param requestIdKyTruoc
     * @param inventoryArea khu vực kiểm kê
     * @return
     */
    List<InventoryVo> balance(Integer requestId, Date fromDate, Date toDate, Integer requestIdKyTruoc, List<Integer> inventoryArea);





    List<InventoryVo> getInventoryRawMaterialData(Integer requestId, Date fromDate, Date toDate);


    /**
     * Lấy dữ liệu LotNo theo tem thùng to, để lưu lại inventory
     * @param requestId
     * @param inventoryArea
     * @param outerLotNoList: danh sách tem to
     * @return
     */
    List<PihInventoryData> getLotNoByInOutLabel(Integer requestId, Integer inventoryArea, List<String> outerLotNoList);

}
