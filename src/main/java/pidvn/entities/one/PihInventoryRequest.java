package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pih_inventory_request")
@EntityListeners(AuditingEntityListener.class)
public class PihInventoryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "req_no")
    private String reqNo;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "remark")
    private String remark;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "calculate_theory_data_date")
    private Date calculateTheoryDataDate;

    @Column(name = "inventory_close_date")
    private Date inventoryCloseDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Date getCalculateTheoryDataDate() {
        return calculateTheoryDataDate;
    }

    public void setCalculateTheoryDataDate(Date calculateTheoryDataDate) {
        this.calculateTheoryDataDate = calculateTheoryDataDate;
    }

    public Date getInventoryCloseDate() {
        return inventoryCloseDate;
    }

    public void setInventoryCloseDate(Date inventoryCloseDate) {
        this.inventoryCloseDate = inventoryCloseDate;
    }
}
