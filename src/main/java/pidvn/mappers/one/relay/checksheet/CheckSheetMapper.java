package pidvn.mappers.one.relay.checksheet;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.checksheet.models.*;

import java.util.List;

@Mapper
public interface CheckSheetMapper {
    List<MasterVo> getCheckSheetMaster(MasterSearchVo searchVo);
    List<ProcessVo> getProcesses(Integer master, String line, String model);
    List<ItemVo> getItems(Integer master, Integer process);
    List<DetailVo> getDetails(String line, Integer master);
}
