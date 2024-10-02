package pidvn.modules.schedule_task.relay.defect_record.models;

import java.util.Date;

public class DefectRecordDto {
    private Integer id;
    private Date date;
    private String line;
    private String model;
    private String defectCode;
    private Float qty;
    private int userId;
    private String shift;
    private String lotNo;
    private String customerCode;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
    private String recordType;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
