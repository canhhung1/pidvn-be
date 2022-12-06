package pidvn.modules.relay.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.RelayInventory;
import pidvn.entities.one.RelayInventoryRequest;
import pidvn.mappers.one.relay.inventory.RelayInventoryMapper;
import pidvn.modules.relay.inventory.models.BalanceVo;
import pidvn.modules.relay.inventory.models.InventoryRequestVo;
import pidvn.repositories.one.MaterialControlsRepo;
import pidvn.repositories.one.RelayInventoryRepo;
import pidvn.repositories.one.RelayInventoryRequestRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelayInventorySvc implements IRelayInventorySvc {

    @Autowired
    private RelayInventoryRepo reIvtRepo;

    @Autowired
    private RelayInventoryRequestRepo reIvtReqRepo;

    @Autowired
    private MaterialControlsRepo materialControlsRepo;

    @Autowired
    private RelayInventoryMapper relayInventoryMapper;

    @Override
    public List<InventoryRequestVo> getInventoryRequests() {
        return this.relayInventoryMapper.getInventoryRequests();
    }

    @Override
    public RelayInventoryRequest getInventoryRequest(Integer id) {
        return this.reIvtReqRepo.findById(id).get();
    }

    @Override
    public RelayInventoryRequest createInventoryRequest(RelayInventoryRequest requestVo) throws ParseException {

        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<RelayInventoryRequest> requests = this.reIvtReqRepo.findByInventoryDate(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
        Integer size = requests.size();
        Integer count = 1;

        StringBuilder reqNo = new StringBuilder("IVT-");
        reqNo.append(new SimpleDateFormat("yyyyMMdd").format(new Date())).append("-").append(count+size);

        RelayInventoryRequest request = new RelayInventoryRequest();
        request.setReqNo(reqNo.toString());
        request.setCutOff(requestVo.getCutOff());
        request.setCreatedBy(requestVo.getCreatedBy());
        request.setInventoryDate(new Date());

        return this.reIvtReqRepo.save(request);
    }

    @Override
    public Map scanMaterial(RelayInventory materialVo) {

        Map result = new HashMap();

        // Kiểm tra NVL đã được nhập vào line chưa
        List<MaterialControls> materials = this.materialControlsRepo.findByClotnoAndRecordType(materialVo.getLotNo(), "CDL");

        if (materials.size() > 0) {
            String message = "Lot: " + materialVo.getLotNo() +" đã nhập vào line lúc: " + materials.get(0).getCreatedAt()
                    + "; Qty: " + materials.get(0).getQty();
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        // Kiểm tra NVL đã kiểm kê chưa
        List<RelayInventory> data = this.reIvtRepo.findByReqNoAndLotNo(materialVo.getReqNo(), materialVo.getLotNo());

        if (data.size() > 0) {
            String message = "Lot: " + materialVo.getLotNo() +" đã kiểm kê lúc: " + data.get(0).getCreatedAt()
                            + "; vị trí: " + materialVo.getLine();
            result.put("status", "ERROR");
            result.put("message", message);
            result.put("data", materialVo);
            return result;
        }

        RelayInventory saveData = this.reIvtRepo.save(materialVo);

        result.put("status", "OK");
        result.put("message", "Save OK");
        result.put("data", saveData);

        return result;
    }

    @Override
    public List<RelayInventory> getRelayInventories(String reqNo) {
        return this.reIvtRepo.findByReqNoOrderByIdDesc(reqNo);
    }

    @Override
    public RelayInventory saveRelayInventory(RelayInventory relayInventory) {
        if (relayInventory != null) {
            RelayInventory obj = this.reIvtRepo.findById(relayInventory.getId()).get();
            obj.setReqNo(relayInventory.getReqNo());
            obj.setRecordType(relayInventory.getRecordType());
            obj.setDate(relayInventory.getDate());
            obj.setLotNo(relayInventory.getLotNo());
            obj.setPartNo(relayInventory.getPartNo());
            obj.setQty(relayInventory.getQty());
            obj.setLine(relayInventory.getLine());
            obj.setCreatedBy(relayInventory.getCreatedBy());
            return this.reIvtRepo.save(obj);
        }

        return this.reIvtRepo.save(relayInventory);
    }

    @Override
    public List<BalanceVo> getBalances(Integer reqId) {

        RelayInventoryRequest request = this.reIvtReqRepo.findById(reqId).get();

        List<BalanceVo> balanceVos = this.relayInventoryMapper.getBalance(request.getReqNo(), request.getCutOff(), request.getInventoryDate());

        return balanceVos;
    }

}
