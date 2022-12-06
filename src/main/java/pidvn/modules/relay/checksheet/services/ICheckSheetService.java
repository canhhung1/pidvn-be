package pidvn.modules.relay.checksheet.services;

import pidvn.entities.one.*;
import pidvn.modules.relay.checksheet.models.*;

import java.util.List;

public interface ICheckSheetService {
    List<MasterVo> getCheckSheetMaster(MasterSearchVo searchVo);
    List<ProcessVo> getProcesses(Integer master, String line, String model);
    List<ItemVo> getItems(Integer master, Integer process);
    CsoItem addItem(ItemVo itemVo);
    List<DetailVo> getDetails(String line, Integer master);
    CsoMaster approveCheckSheet(MasterVo masterVo);
    CsoDetail editDetail(DetailVo detailVo);
    List<CsoModel> getModels();
    CsoProcess updateProcess(ProcessVo processVo);

}
