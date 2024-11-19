package pidvn.modules.relay.relay_process_recording.models;

import java.util.Date;

public class RequestDto {
    private Integer id;
    private String requestNo;
    private int totalLotRequest;
    private int totalLotReceive;
    private Date createdAt;
    private Date date;
    private String line;
    private String parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public int getTotalLotRequest() {
        return totalLotRequest;
    }

    public void setTotalLotRequest(int totalLotRequest) {
        this.totalLotRequest = totalLotRequest;
    }

    public int getTotalLotReceive() {
        return totalLotReceive;
    }

    public void setTotalLotReceive(int totalLotReceive) {
        this.totalLotReceive = totalLotReceive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
