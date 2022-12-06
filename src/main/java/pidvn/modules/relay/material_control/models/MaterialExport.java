package pidvn.modules.relay.material_control.models;

import java.util.Date;

public class MaterialExport {
    private Integer id;
    private String qaCardLine;
    private String qaCardShift;
    private String model;
    private Date createdAt;
    private Date qaCardDate;
    private Date lotDate;
    private String qaCard;
    private String lotLine;
    private String lotShift;
    private String lotNo;
    private String partNo;
    private Float qty;
    private String username;
    private Integer rowNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQaCardLine() {
        return qaCardLine;
    }

    public void setQaCardLine(String qaCardLine) {
        this.qaCardLine = qaCardLine;
    }

    public String getQaCardShift() {
        return qaCardShift;
    }

    public void setQaCardShift(String qaCardShift) {
        this.qaCardShift = qaCardShift;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getQaCardDate() {
        return qaCardDate;
    }

    public void setQaCardDate(Date qaCardDate) {
        this.qaCardDate = qaCardDate;
    }

    public Date getLotDate() {
        return lotDate;
    }

    public void setLotDate(Date lotDate) {
        this.lotDate = lotDate;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public String getLotLine() {
        return lotLine;
    }

    public void setLotLine(String lotLine) {
        this.lotLine = lotLine;
    }

    public String getLotShift() {
        return lotShift;
    }

    public void setLotShift(String lotShift) {
        this.lotShift = lotShift;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }
}
