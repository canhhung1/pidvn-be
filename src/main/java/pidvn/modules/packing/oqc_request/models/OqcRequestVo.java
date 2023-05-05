package pidvn.modules.packing.oqc_request.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OqcRequestVo {

    private Integer id;
    private String reqNo;
    private String qaCard;
    private Float qty;
    private String createdBy;
    private Integer requestStatusId;
    private Integer priority;
    private Float sortingQty;
    private String remark;
    private Date deliveryDate;

    @JsonProperty
    private boolean isSorting;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getRequestStatusId() {
        return requestStatusId;
    }

    public void setRequestStatusId(Integer requestStatusId) {
        this.requestStatusId = requestStatusId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Float getSortingQty() {
        return sortingQty;
    }

    public void setSortingQty(Float sortingQty) {
        this.sortingQty = sortingQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSorting() {
        return isSorting;
    }

    public void setSorting(boolean sorting) {
        isSorting = sorting;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
