package pidvn.modules.spare_part.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.spare_part.SparePartMapper;
import pidvn.modules.spare_part.models.SparePartRecordVo;
import pidvn.repositories.one.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SparePartSvc implements ISparePartSvc {

    Logger logger = LoggerFactory.getLogger(SparePartSvc.class);

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private SparePartRepo sparePartRepo;

    @Autowired
    private SparePartRecordRepo sparePartRecordRepo;

    @Autowired
    private SparePartInventoryRequestRepo sparePartInventoryRequestRepo;

    @Autowired
    private SparePartInventoryDataRepo sparePartInventoryDataRepo;

    @Autowired
    private SparePartMapper sparePartMapper;

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAllByOrderByIdDesc();
    }

    @Override
    public List<SparePart> getSpareParts() {
        return this.sparePartRepo.findAll();
    }

    @Override
    public List<SparePartRecordVo> getSparePartRecords() {
        return this.sparePartMapper.getSparePartRecords();
    }

    @Override
    public SparePartRecord saveSparePartRecord(SparePartRecord sparePartRecord) {
        return this.sparePartRecordRepo.save(sparePartRecord);
    }

    @Override
    public SparePartInventoryRequest saveSparePartInventoryRequest(SparePartInventoryRequest request) throws Exception {

        /**
         * Nếu đã có phiếu kiểm kê trong tháng rồi thì ko tạo nữa
         */
        List<SparePartInventoryRequest> requests = this.sparePartInventoryRequestRepo.findByCurrentMonth();

        if (requests.size() > 0) {
            throw new Exception("Đã có phiếu kiểm kê tháng này");
        }

        return this.sparePartInventoryRequestRepo.save(request);
    }

    @Override
    public List<SparePartInventoryRequest> getSparePartInventoryRequests() {
        return this.sparePartInventoryRequestRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Map saveInventoryData(List<SparePartInventoryData> sparePartInventoryDataList) {

        List<SparePartInventoryData> resultOK = new ArrayList<>();
        List<SparePartInventoryData> resultNG = new ArrayList<>();

        for (SparePartInventoryData item: sparePartInventoryDataList) {
            try {
                SparePartInventoryData ivtData = this.sparePartInventoryDataRepo.save(item);
                resultOK.add(ivtData);
            }catch (Exception e) {
                logger.debug(e.toString());
                resultNG.add(item);
            }
        }

        Map result = new HashMap();
        result.put("resultOK", resultOK);
        result.put("resultNG", resultNG);

        return result;
    }
}
