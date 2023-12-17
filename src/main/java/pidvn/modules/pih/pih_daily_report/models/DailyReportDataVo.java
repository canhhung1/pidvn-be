package pidvn.modules.pih.pih_daily_report.models;

import java.util.Date;

public class DailyReportDataVo {

    private Integer id;
    private String line;
    private Date date;
    private Date reportDate;
    private String model;
    private Integer qtyShiftA;
    private Integer qtyShiftB;
    private Integer targetQty;
    private Integer actualQty;
    private Integer dieAQty;
    private Integer dieBQty;
    private String remark;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQtyShiftA() {
        return qtyShiftA;
    }

    public void setQtyShiftA(Integer qtyShiftA) {
        this.qtyShiftA = qtyShiftA;
    }

    public Integer getQtyShiftB() {
        return qtyShiftB;
    }

    public void setQtyShiftB(Integer qtyShiftB) {
        this.qtyShiftB = qtyShiftB;
    }

    public Integer getTargetQty() {
        return targetQty;
    }

    public void setTargetQty(Integer targetQty) {
        this.targetQty = targetQty;
    }

    public Integer getActualQty() {
        return actualQty;
    }

    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public Integer getDieAQty() {
        return dieAQty;
    }

    public void setDieAQty(Integer dieAQty) {
        this.dieAQty = dieAQty;
    }

    public Integer getDieBQty() {
        return dieBQty;
    }

    public void setDieBQty(Integer dieBQty) {
        this.dieBQty = dieBQty;
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
}
