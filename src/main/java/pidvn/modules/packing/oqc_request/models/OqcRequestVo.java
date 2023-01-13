package pidvn.modules.packing.oqc_request.models;

public class OqcRequestVo {

    private String reqNo;
    private String qaCard;
    private String qty;
    private String createdBy;
    private String oqcRequestStatus;
    private Integer priority;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getOqcRequestStatus() {
        return oqcRequestStatus;
    }

    public void setOqcRequestStatus(String oqcRequestStatus) {
        this.oqcRequestStatus = oqcRequestStatus;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
