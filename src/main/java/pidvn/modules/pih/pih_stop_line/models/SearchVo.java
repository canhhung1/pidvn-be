package pidvn.modules.pih.pih_stop_line.models;

import java.util.Date;
import java.util.List;

public class SearchVo {
    private Date fromDate;
    private Date toDate;
    private Integer line;
    private Integer shift;
    private Integer group;
    private Integer type;
    private List<Date> startTimeRange;
    private List<Date> createdAtRange;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Date> getStartTimeRange() {
        return startTimeRange;
    }

    public void setStartTimeRange(List<Date> startTimeRange) {
        this.startTimeRange = startTimeRange;
    }

    public List<Date> getCreatedAtRange() {
        return createdAtRange;
    }

    public void setCreatedAtRange(List<Date> createdAtRange) {
        this.createdAtRange = createdAtRange;
    }
}
