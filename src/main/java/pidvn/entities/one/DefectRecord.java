package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "defect_records")
@EntityListeners(AuditingEntityListener.class)
public class DefectRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "model")
    private String model;
    @Column(name = "line")
    private String line;
    @Column(name = "date")
    private Date date;
    @Column(name = "shift")
    private String shift;
    @Column(name = "lot_no")
    private String lotNo;
    @Column(name = "defect_code")
    private String defectCode;
    @Column(name = "qty")
    private Double qty;
    @Column(name = "st_qty")
    private Integer stQty;
    @Column(name = "used_qty")
    private Integer usedQty;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "remark")
    private String remark;
    @CreatedDate
    @Column(name = "created_at")
    private Date createAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "lot_remark")
    private String lotRemark;
    @Column(name = "defect_remark")
    private String defectRemark;
    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Integer getStQty() {
        return stQty;
    }

    public void setStQty(Integer stQty) {
        this.stQty = stQty;
    }

    public Integer getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(Integer usedQty) {
        this.usedQty = usedQty;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLotRemark() {
        return lotRemark;
    }

    public void setLotRemark(String lotRemark) {
        this.lotRemark = lotRemark;
    }

    public String getDefectRemark() {
        return defectRemark;
    }

    public void setDefectRemark(String defectRemark) {
        this.defectRemark = defectRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
