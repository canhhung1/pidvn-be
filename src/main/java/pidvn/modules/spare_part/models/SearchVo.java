package pidvn.modules.spare_part.models;

import java.util.Date;
import java.util.List;

public class SearchVo {

    private List<Date> dateRange;

    public List<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<Date> dateRange) {
        this.dateRange = dateRange;
    }
}
