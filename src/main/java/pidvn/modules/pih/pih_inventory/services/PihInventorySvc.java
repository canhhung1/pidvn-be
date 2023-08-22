package pidvn.modules.pih.pih_inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.repositories.one.PihInventoryDataRepo;
import pidvn.repositories.one.PihInventoryRequestRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PihInventorySvc implements IPihInventorySvc {

    Logger logger = LoggerFactory.getLogger(PihInventorySvc.class);

    @Autowired
    private PihInventoryRequestRepo pihInventoryRequestRepo;

    @Autowired
    private PihInventoryDataRepo pihInventoryDataRepo;

    @Override
    public List<PihInventoryRequest> getInventoryRequests() {
        return this.pihInventoryRequestRepo.findAllByOrderByIdDesc();
    }

    @Override
    public PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception {

        // TODO: nếu đã tồn tại request thì ko tạo mới

        PihInventoryRequest request = this.pihInventoryRequestRepo.findByReqNo(ivtReq.getReqNo());

        if (request != null) {
            throw new Exception("Request đã tồn tại !");
        }

        return this.pihInventoryRequestRepo.save(ivtReq);
    }

    @Override
    public Map saveInventoryData(List<PihInventoryData> inventoryData) {

        List<PihInventoryData> resultOK = new ArrayList<>();
        List<PihInventoryData> resultNG = new ArrayList<>();

        for (PihInventoryData item : inventoryData) {
            try {
                PihInventoryData ivtData = this.pihInventoryDataRepo.save(item);
                resultOK.add(ivtData);
            } catch (Exception e) {
                logger.debug(e.toString());
                resultNG.add(item);
            }
        }

        Map result = new HashMap();
        result.put("resultOK", resultOK);
        result.put("resultNG", resultNG);

        return result;
    }

    @Override
    public List<PihInventoryData> getInventoryDataByRequestId(Integer requestId) {
        return this.pihInventoryDataRepo.findByRequestId(requestId);
    }


}
