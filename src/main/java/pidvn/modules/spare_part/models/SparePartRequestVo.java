package pidvn.modules.spare_part.models;

import java.util.Date;

public class SparePartRequestVo {
    private Integer id;
    private String requestNo;
    private Date date;
    private Integer sectionId;
    private String sectionName;
    private Integer subsectionId;
    private String subsectionName;
    private String partNumber;
    private String partName;
    private Float qty;
    private String createdBy;
    private String createdByName;
    private Float requestQty;
    private Float kittingQty;
    private String factoryCode;
    private String factoryName;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(Integer subsectionId) {
        this.subsectionId = subsectionId;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
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

    public Float getRequestQty() {
        return requestQty;
    }

    public void setRequestQty(Float requestQty) {
        this.requestQty = requestQty;
    }

    public Float getKittingQty() {
        return kittingQty;
    }

    public void setKittingQty(Float kittingQty) {
        this.kittingQty = kittingQty;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
