package pidvn.modules.spare_part.models;

import java.util.Date;
import java.util.List;

public class SearchVo {

    private List<Date> dateRange;

    private Date date;

    private Integer chartType;

    public List<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<Date> dateRange) {
        this.dateRange = dateRange;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getChartType() {
        return chartType;
    }

    public void setChartType(Integer chartType) {
        this.chartType = chartType;
    }
}
