package pidvn.modules.pih.pih_stop_line.services;

import pidvn.entities.one.*;
import pidvn.modules.pih.pih_stop_line.models.LineVo;
import pidvn.modules.pih.pih_stop_line.models.SearchVo;
import pidvn.modules.pih.pih_stop_line.models.StopTime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPihStopLineSvc {

    List<ProductType> getProductTypes(Integer productId);
    List<LineVo> getLines();
    List<Shifts> getShifts();

    List<StopTypes> getStopTypes();
    List<StopGroups> getStopGroups();
    List<StopItems> getStopItems();


    StopTimes createStopTime(StopTimes stopTime);
    StopTimes updateStopTime(StopTimes stopTimes);
    List<StopTime>getStopTimes(SearchVo searchVo);
    Map deleteStopTime(Integer id);

    Map getProductTypeIdByUser(String username) throws IOException;
}
