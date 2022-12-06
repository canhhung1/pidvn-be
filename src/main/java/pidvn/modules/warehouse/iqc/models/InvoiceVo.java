package pidvn.modules.warehouse.iqc.models;

import java.util.Date;

public class InvoiceVo {

    private String invoice;
    private String lotGroup;
    private String model;
    private Integer iqcStatus;
    private String iqcStatusName;
    private Date warehouseDate;
    private Date createdAt;
    private Date updatedAt;
    private String requestNo;
    private String supplier;
    private String evaluate;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getIqcStatus() {
        return iqcStatus;
    }

    public void setIqcStatus(Integer iqcStatus) {
        this.iqcStatus = iqcStatus;
    }

    public String getIqcStatusName() {
        return iqcStatusName;
    }

    public void setIqcStatusName(String iqcStatusName) {
        this.iqcStatusName = iqcStatusName;
    }

    public Date getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(Date warehouseDate) {
        this.warehouseDate = warehouseDate;
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

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
