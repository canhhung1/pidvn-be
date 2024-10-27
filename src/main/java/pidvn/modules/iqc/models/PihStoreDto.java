package pidvn.modules.iqc.models;

import java.util.Date;

public class PihStoreDto {
    private String model;
    private String lotGroup;
    private String lotNo;
    private Float wh;
    private Date iqcDate;
    private Integer daySinceIqc;
    private String classified;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Float getWh() {
        return wh;
    }

    public void setWh(Float wh) {
        this.wh = wh;
    }

    public Date getIqcDate() {
        return iqcDate;
    }

    public void setIqcDate(Date iqcDate) {
        this.iqcDate = iqcDate;
    }

    public Integer getDaySinceIqc() {
        return daySinceIqc;
    }

    public void setDaySinceIqc(Integer daySinceIqc) {
        this.daySinceIqc = daySinceIqc;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }
}
