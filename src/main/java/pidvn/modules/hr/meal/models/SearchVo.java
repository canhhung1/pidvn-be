package pidvn.modules.hr.meal.models;

import java.util.Date;
import java.util.List;

public class SearchVo {

    private List<Date> timeLogRange;

    public List<Date> getTimeLogRange() {
        return timeLogRange;
    }

    public void setTimeLogRange(List<Date> timeLogRange) {
        this.timeLogRange = timeLogRange;
    }
}
