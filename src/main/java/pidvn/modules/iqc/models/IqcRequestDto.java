package pidvn.modules.iqc.models;

import java.util.Date;
import java.util.List;

public class IqcRequestDto {

    private Integer id;
    private String requestNo;
    private String invoice;
    private String slipNo;
    private Integer status;
    private String statusName;
    private String supplier;
    private String type;
    private String typeName;
    private String requestedBy;
    private Integer requestedById;
    private String requestedByName;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
    public String classParam;
    public List<IqcResultDto> iqcResults;
    public List<String> lotGroups;
    public List<String> lotNos;


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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Integer getRequestedById() {
        return requestedById;
    }

    public void setRequestedById(Integer requestedById) {
        this.requestedById = requestedById;
    }

    public String getRequestedByName() {
        return requestedByName;
    }

    public void setRequestedByName(String requestedByName) {
        this.requestedByName = requestedByName;
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

    public List<IqcResultDto> getIqcResults() {
        return iqcResults;
    }

    public void setIqcResults(List<IqcResultDto> iqcResults) {
        this.iqcResults = iqcResults;
    }

    public String getClassParam() {
        return classParam;
    }

    public void setClassParam(String classParam) {
        this.classParam = classParam;
    }

    public List<String> getLotGroups() {
        return lotGroups;
    }

    public void setLotGroups(List<String> lotGroups) {
        this.lotGroups = lotGroups;
    }

    public List<String> getLotNos() {
        return lotNos;
    }

    public void setLotNos(List<String> lotNos) {
        this.lotNos = lotNos;
    }


}
