package pidvn.modules.hr.waste_mng.models;

import java.util.Date;

public class WasteSearchVo {
    private Integer id;
    private Integer wasteMaster;
    private Integer wasteGroup;
    private Integer wasteType;
    private Date fromDate;
    private Date toDate;

    private String sqlType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWasteMaster() {
        return wasteMaster;
    }

    public void setWasteMaster(Integer wasteMaster) {
        this.wasteMaster = wasteMaster;
    }

    public Integer getWasteGroup() {
        return wasteGroup;
    }

    public void setWasteGroup(Integer wasteGroup) {
        this.wasteGroup = wasteGroup;
    }

    public Integer getWasteType() {
        return wasteType;
    }

    public void setWasteType(Integer wasteType) {
        this.wasteType = wasteType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
}
