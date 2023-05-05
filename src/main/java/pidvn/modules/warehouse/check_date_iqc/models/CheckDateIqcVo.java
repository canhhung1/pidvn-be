package pidvn.modules.warehouse.check_date_iqc.models;

import java.util.Date;

public class CheckDateIqcVo {
    private String month;
    private Date date;
    private String model;
    private String lotNo;
    private Float qty;
    private String lotGroup;
    private Date updateAt;
    private String concatGroupsIqcMaster;
    private String result1;
    private String result2;
    private String concatGroupsIqcResults;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getLotGroup() {
        return lotGroup;
    }

    public void setLotGroup(String lotGroup) {
        this.lotGroup = lotGroup;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getConcatGroupsIqcMaster() {
        return concatGroupsIqcMaster;
    }

    public void setConcatGroupsIqcMaster(String concatGroupsIqcMaster) {
        this.concatGroupsIqcMaster = concatGroupsIqcMaster;
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

    public String getConcatGroupsIqcResults() {
        return concatGroupsIqcResults;
    }

    public void setConcatGroupsIqcResults(String concatGroupsIqcResults) {
        this.concatGroupsIqcResults = concatGroupsIqcResults;
    }
}
