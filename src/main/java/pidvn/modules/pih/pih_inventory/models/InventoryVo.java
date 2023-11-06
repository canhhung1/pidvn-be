package pidvn.modules.pih.pih_inventory.models;

import java.util.Date;

public class InventoryVo {

    private Integer id;
    private Integer requestId;
    private String lotNo;
    private Float qty;
    private Float sysQty;
    private Float ivtQty;
    private String classified;
    private String partNo;
    private Date createdAt;
    private Date updatedAt;
    private String inventoryArea;
    private Float balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getSysQty() {
        return sysQty;
    }

    public void setSysQty(Float sysQty) {
        this.sysQty = sysQty;
    }

    public Float getIvtQty() {
        return ivtQty;
    }

    public void setIvtQty(Float ivtQty) {
        this.ivtQty = ivtQty;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
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

    public String getInventoryArea() {
        return inventoryArea;
    }

    public void setInventoryArea(String inventoryArea) {
        this.inventoryArea = inventoryArea;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
