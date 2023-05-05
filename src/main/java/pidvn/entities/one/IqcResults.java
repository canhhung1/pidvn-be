package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pur_wh_inventories")
@EntityListeners(AuditingEntityListener.class)
public class IqcResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "month")
    private String month;

    @Column(name = "date")
    private Date date;

    @Column(name = "model")
    private String model;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "qty")
    private Float qty;

    @Column(name = "adjust_qty")
    private Float adjustQty;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "remark")
    private String remark;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "status")
    private String status;

    @Column(name = "iqc")
    private String iqc;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public void setAdjustQty(Float adjustQty) {
        this.adjustQty = adjustQty;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIqc(String iqc) {
        this.iqc = iqc;
    }

}
