package pidvn.modules.spare_part.models;

import java.util.Date;

public class SparePartRequestVo {
    private Integer id;
    private String requestNo;
    private Date date;
    private Integer sectionId;
    private String sectionName;
    private String partNumber;
    private String partName;
    private Float qty;
    private String createdBy;
    private String createdByName;
    private Float requestQty;
    private Float kittingQty;

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
}
