package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inout_labels")
@EntityListeners(AuditingEntityListener.class)
public class InoutLabels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "outer_lot_no")
    private String outerLotNo;

    @Column(name = "inner_lot_no")
    private String innerLotNo;

    @Column(name = "model")
    private String model;

    @Column(name = "line")
    private String line;

    @Column(name = "shift")
    private String shift;

    @Column(name = "date")
    private Date date;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "inner_labels")
    private String innerLabels;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOuterLotNo() {
        return outerLotNo;
    }

    public void setOuterLotNo(String outerLotNo) {
        this.outerLotNo = outerLotNo;
    }

    public String getInnerLotNo() {
        return innerLotNo;
    }

    public void setInnerLotNo(String innerLotNo) {
        this.innerLotNo = innerLotNo;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getInnerLabels() {
        return innerLabels;
    }

    public void setInnerLabels(String innerLabels) {
        this.innerLabels = innerLabels;
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
}
