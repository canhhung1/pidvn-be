package pidvn.modules.relay.relay_process_recording.models;

import java.util.Date;
import java.util.List;

public class SearchDto {
    private String requestNo;
    private Date [] dateRange;
    private String recordType;
    private String slipNo;
    private List<String> lotNos;

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

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public List<String> getLotNos() {
        return lotNos;
    }

    public void setLotNos(List<String> lotNos) {
        this.lotNos = lotNos;
    }
}
