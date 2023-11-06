package pidvn.modules.pih.pih_inventory.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Lots;
import pidvn.entities.one.PihInventoryData;
import pidvn.entities.one.PihInventoryRequest;
import pidvn.entities.one.ProductType;
import pidvn.mappers.one.pih.pih_inventory.PihInventoryMapper;
import pidvn.modules.pih.pih_inventory.models.InventoryVo;
import pidvn.repositories.one.LotsRepo;
import pidvn.repositories.one.PihInventoryDataRepo;
import pidvn.repositories.one.PihInventoryRequestRepo;
import pidvn.repositories.one.ProductTypeRepo;

import java.util.*;

@Service
public class PihInventorySvc implements IPihInventorySvc {

    Logger logger = LoggerFactory.getLogger(PihInventorySvc.class);

    @Autowired
    private PihInventoryRequestRepo pihInventoryRequestRepo;

    @Autowired
    private PihInventoryDataRepo pihInventoryDataRepo;

    @Autowired
    private LotsRepo lotsRepo;

    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Autowired
    private PihInventoryMapper pihInventoryMapper;

    @Override
    public List<ProductType> getInventoryArea() {
        List<Integer> productIds = Arrays.asList(4,6);
        return this.productTypeRepo.findByProductIdIn(productIds);
    }

    @Override
    public List<PihInventoryRequest> getInventoryRequests() {
        return this.pihInventoryRequestRepo.findAllByOrderByIdDesc();
    }

    @Override
    public PihInventoryRequest createInventoryRequest(PihInventoryRequest ivtReq) throws Exception {

        // TODO: nếu đã tồn tại request thì ko tạo mới

        PihInventoryRequest request = this.pihInventoryRequestRepo.findByReqNo(ivtReq.getReqNo());

        List<PihInventoryRequest> requests = this.pihInventoryRequestRepo.findByCurrentMonth();

        if (requests.size() > 0) {
            throw new Exception("Đã có phiếu kiểm kê tháng này");
        }

        return this.pihInventoryRequestRepo.save(ivtReq);
    }

    @Override
    public Map saveListInventoryData(List<PihInventoryData> inventoryData) {

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
    public PihInventoryData saveInventoryData(PihInventoryData inventoryData) {
        return this.pihInventoryDataRepo.save(inventoryData);
    }

    @Override
    public List<InventoryVo> getInventoryDataByRequestId(Integer requestId) {
        return this.pihInventoryMapper.getInventoryData(requestId);
    }

    @Override
    public Lots scanLabel(String lotNo) {
        return this.lotsRepo.findByLotNo(lotNo);
    }

    @Override
    public List<InventoryVo> balance(Integer requestId, List<Integer> inventoryArea) {

        /**
         * Tìm ngày nhập tồn đầu kỳ trước
         */
        List<PihInventoryRequest> requests = this.pihInventoryRequestRepo.findAllByOrderByIdDesc(requestId);

        Date dateKiTruoc = requests.get(1).getCreatedAt();

        Date dateKiNay = requests.get(0).getCreatedAt();

        Integer requestIdKyTruoc = requests.get(1).getId();

        return this.pihInventoryMapper.balance(requestId, dateKiTruoc, dateKiNay, requestIdKyTruoc, inventoryArea);
    }


}
