package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "iqc_data_master")
@EntityListeners(AuditingEntityListener.class)
public class IqcDataMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "request_no")
    private String requestNo;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "lot_group")
    private String lotGroup;

    @Column(name = "model")
    private String model;

    @Column(name = "result1")
    private String result1;

    @Column(name = "result2")
    private String result2;

    @Column(name = "result3")
    private String result3;

    @Column(name = "remark")
    private String remark;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "level_of_control_ngoai_quan")
    private String levelOfControlNgoaiQuan;
    @Column(name = "level_of_control_kich_thuoc")

    private String levelOfControlKichThuoc;

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

    public String getLotGroup() {
        return lotGroup;
    }

    public void setLotGroup(String lotGroup) {
        this.lotGroup = lotGroup;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLevelOfControlNgoaiQuan() {
        return levelOfControlNgoaiQuan;
    }

    public void setLevelOfControlNgoaiQuan(String levelOfControlNgoaiQuan) {
        this.levelOfControlNgoaiQuan = levelOfControlNgoaiQuan;
    }

    public String getLevelOfControlKichThuoc() {
        return levelOfControlKichThuoc;
    }

    public void setLevelOfControlKichThuoc(String levelOfControlKichThuoc) {
        this.levelOfControlKichThuoc = levelOfControlKichThuoc;
    }
}
