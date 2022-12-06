package pidvn.modules.hr.waste_mng.models;

import java.util.Date;
import java.util.List;

public class WasteDataVo {
    private Integer id;
    private String ids;
    private Integer wasteMaster;
    private Integer wasteDetail;
    private String createdBy;
    private String createdByName;
    private Date createdAt;
    private Integer wasteGroup;
    private String wasteGroupName;
    private Integer wasteType;
    private String wasteTypeName;
    private Float price;
    private Float weight;
    private Float netWeight;
    private Float amount;
    private String unit;
    private String wasteTypeCode;
    private Integer handleCompany;
    private String handleCompanyName;
    private String handleCompanyLabel;
    private Integer handleMethod;
    private String handleMethodName;
    private Integer wasteStatus;
    private String wasteStatusName;
    private List<Integer> images;
    private String sealNo;
    private String packagingNo;
    private Integer rowNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getWasteMaster() {
        return wasteMaster;
    }

    public void setWasteMaster(Integer wasteMaster) {
        this.wasteMaster = wasteMaster;
    }

    public Integer getWasteDetail() {
        return wasteDetail;
    }

    public void setWasteDetail(Integer wasteDetail) {
        this.wasteDetail = wasteDetail;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getWasteGroup() {
        return wasteGroup;
    }

    public void setWasteGroup(Integer wasteGroup) {
        this.wasteGroup = wasteGroup;
    }

    public String getWasteGroupName() {
        return wasteGroupName;
    }

    public void setWasteGroupName(String wasteGroupName) {
        this.wasteGroupName = wasteGroupName;
    }

    public Integer getWasteType() {
        return wasteType;
    }

    public void setWasteType(Integer wasteType) {
        this.wasteType = wasteType;
    }

    public String getWasteTypeName() {
        return wasteTypeName;
    }

    public void setWasteTypeName(String wasteTypeName) {
        this.wasteTypeName = wasteTypeName;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Float netWeight) {
        this.netWeight = netWeight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWasteTypeCode() {
        return wasteTypeCode;
    }

    public void setWasteTypeCode(String wasteTypeCode) {
        this.wasteTypeCode = wasteTypeCode;
    }

    public Integer getHandleCompany() {
        return handleCompany;
    }

    public void setHandleCompany(Integer handleCompany) {
        this.handleCompany = handleCompany;
    }

    public String getHandleCompanyName() {
        return handleCompanyName;
    }

    public void setHandleCompanyName(String handleCompanyName) {
        this.handleCompanyName = handleCompanyName;
    }

    public String getHandleCompanyLabel() {
        return handleCompanyLabel;
    }

    public void setHandleCompanyLabel(String handleCompanyLabel) {
        this.handleCompanyLabel = handleCompanyLabel;
    }

    public Integer getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(Integer handleMethod) {
        this.handleMethod = handleMethod;
    }

    public String getHandleMethodName() {
        return handleMethodName;
    }

    public void setHandleMethodName(String handleMethodName) {
        this.handleMethodName = handleMethodName;
    }

    public Integer getWasteStatus() {
        return wasteStatus;
    }

    public void setWasteStatus(Integer wasteStatus) {
        this.wasteStatus = wasteStatus;
    }

    public String getWasteStatusName() {
        return wasteStatusName;
    }

    public void setWasteStatusName(String wasteStatusName) {
        this.wasteStatusName = wasteStatusName;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public String getSealNo() {
        return sealNo;
    }

    public void setSealNo(String sealNo) {
        this.sealNo = sealNo;
    }

    public String getPackagingNo() {
        return packagingNo;
    }

    public void setPackagingNo(String packagingNo) {
        this.packagingNo = packagingNo;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }
}
