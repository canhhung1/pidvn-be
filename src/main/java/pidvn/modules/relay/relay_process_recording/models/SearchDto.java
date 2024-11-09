package pidvn.modules.relay.relay_process_recording.models;

import java.util.Date;

public class SearchDto {
    private String requestNo;
    private Date [] dateRange;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Date[] getDateRange() {
        return dateRange;
    }

    public void setDateRange(Date[] dateRange) {
        this.dateRange = dateRange;
    }
}
