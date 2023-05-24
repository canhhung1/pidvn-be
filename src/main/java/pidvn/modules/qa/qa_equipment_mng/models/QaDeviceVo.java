package pidvn.modules.qa.qa_equipment_mng.models;
import java.util.Date;

public class QaDeviceVo {

    private Integer id;
    private String controlNo;
    private String equipmentName;
    private String serialNo;
    private String typeNo;
    private String manufacturer;
    private String rangeOfMeasuring;
    private String distributionLocation;
    private Date createdAt;
    private Date updatedAt;
    private String printCode;
    private Date date;
    private Date due;
    private Date labelCreatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRangeOfMeasuring() {
        return rangeOfMeasuring;
    }

    public void setRangeOfMeasuring(String rangeOfMeasuring) {
        this.rangeOfMeasuring = rangeOfMeasuring;
    }

    public String getDistributionLocation() {
        return distributionLocation;
    }

    public void setDistributionLocation(String distributionLocation) {
        this.distributionLocation = distributionLocation;
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

    public String getPrintCode() {
        return printCode;
    }

    public void setPrintCode(String printCode) {
        this.printCode = printCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getLabelCreatedAt() {
        return labelCreatedAt;
    }

    public void setLabelCreatedAt(Date labelCreatedAt) {
        this.labelCreatedAt = labelCreatedAt;
    }
}
