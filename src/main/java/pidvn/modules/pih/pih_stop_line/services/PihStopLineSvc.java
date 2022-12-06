package pidvn.modules.pih.pih_stop_line.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.pih.pih_stop_line.PihStopLineMapper;
import pidvn.modules.pih.pih_stop_line.models.LineVo;
import pidvn.modules.pih.pih_stop_line.models.SearchVo;
import pidvn.modules.pih.pih_stop_line.models.StopTime;
import pidvn.repositories.one.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PihStopLineSvc implements IPihStopLineSvc {

    @Autowired
    private PihStopLineMapper pihStopLineMapper;

    @Autowired
    private ShiftsRepo shiftsRepo;

    @Autowired
    private StopTypesRepo stopTypesRepo;

    @Autowired
    private StopGroupsRepo stopGroupsRepo;

    @Autowired
    private StopItemsRepo stopItemsRepo;

    @Autowired
    private StopTimesRepo stopTimesRepo;

    @Override
    public List<LineVo> getLines() {
        return this.pihStopLineMapper.getLines();
    }

    @Override
    public List<Shifts> getShifts() {
        return this.shiftsRepo.findAll();
    }

    @Override
    public List<StopTypes> getStopTypes() {
        return this.stopTypesRepo.findAll();
    }

    @Override
    public List<StopGroups> getStopGroups() {
        return this.stopGroupsRepo.findAll();
    }

    @Override
    public List<StopItems> getStopItems() {
        return this.stopItemsRepo.findAll();
    }


    @Override
    public StopTimes createStopTime(StopTimes stopTime) {
        return this.stopTimesRepo.save(stopTime);
    }

    @Override
    public StopTimes updateStopTime(StopTimes stopTimes) {
        StopTimes stopTime = this.stopTimesRepo.findById(stopTimes.getId()).get();
        stopTime.setStopItemId(stopTimes.getStopItemId());
        stopTime.setDate(stopTimes.getDate());
        stopTime.setStartTime(stopTimes.getStartTime());
        stopTime.setStopTime(stopTimes.getStopTime());
        stopTime.setLine(stopTimes.getLine());
        stopTime.setShift(stopTimes.getShift());
        stopTime.setRemark(stopTimes.getRemark());
        return this.stopTimesRepo.save(stopTime);
    }

    @Override
    public List<StopTime> getStopTimes(SearchVo searchVo) {
        return this.pihStopLineMapper.getStopTimes(searchVo);
    }

    @Override
    public Map deleteStopTime(Integer id) {
        Map result = new HashMap();

        this.stopTimesRepo.deleteById(id);
        result.put("response", "Deleted Stop Time Id : " + id);
        return result;
    }
}
