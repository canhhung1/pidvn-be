package pidvn.modules.relay.checksheet.models;

import java.util.Date;

public class MasterVo {
    private Integer id;
    private String qaCard;
    private String line;
    private String model;
    private String date;
    private String shift;
    private String approveBy;
    private String approveByName;
    private String approveStatus;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
    private Integer checkedAmount;
    private Integer itemTotal;
    private Integer okTotal;
    private Integer ngTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getApproveByName() {
        return approveByName;
    }

    public void setApproveByName(String approveByName) {
        this.approveByName = approveByName;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Integer checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public Integer getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Integer getOkTotal() {
        return okTotal;
    }

    public void setOkTotal(Integer okTotal) {
        this.okTotal = okTotal;
    }

    public Integer getNgTotal() {
        return ngTotal;
    }

    public void setNgTotal(Integer ngTotal) {
        this.ngTotal = ngTotal;
    }
}
