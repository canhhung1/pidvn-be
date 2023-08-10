package pidvn.modules.hr.meal.models;

import java.util.Date;
import java.util.List;

public class SearchVo {

    private List<Date> timeLogRange;

    private String empCode;

    public List<Date> getTimeLogRange() {
        return timeLogRange;
    }

    public void setTimeLogRange(List<Date> timeLogRange) {
        this.timeLogRange = timeLogRange;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
}
