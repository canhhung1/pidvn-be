package pidvn.modules.qa.iqc_check.models;

import java.util.Date;

public class IqcDataVo {

    private Integer id;
    private String requestNo;
    private String invoice;
    private String lotGroup;
    private String lotNo;
    private String model;
    private Double qty;
    private Integer amount;
    private String supplier;
    private String result1;
    private String result2;
    private String result3;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String createdByName;
    private Date iqcAt;
    private Integer checkedAmount;
    private Date requestedAt;

    private String levelOfControlNgoaiQuan;

    private String levelOfControlKichThuoc;

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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getLotGroup() {
        return lotGroup;
    }

    public void setLotGroup(String lotGroup) {
        this.lotGroup = lotGroup;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Date getIqcAt() {
        return iqcAt;
    }

    public void setIqcAt(Date iqcAt) {
        this.iqcAt = iqcAt;
    }

    public Integer getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Integer checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getLevelOfControlNgoaiQuan() {
        return levelOfControlNgoaiQuan;
    }

    public void setLevelOfControlNgoaiQuan(String levelOfControlNgoaiQuan) {
        this.levelOfControlNgoaiQuan = levelOfControlNgoaiQuan;
    }

    public String getLevelOfControlKichThuoc() {
        return levelOfControlKichThuoc;
    }

    public void setLevelOfControlKichThuoc(String levelOfControlKichThuoc) {
        this.levelOfControlKichThuoc = levelOfControlKichThuoc;
    }
}
