package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "waste_detail_data")
@EntityListeners(AuditingEntityListener.class)
public class WasteDetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "waste_master")
    private Integer wasteMaster;

    @Column(name = "waste_group")
    private Integer wasteGroup;

    @Column(name = "waste_type")
    private Integer wasteType;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "net_weight")
    private Float netWeight;

    @Column(name = "price")
    private Float price;

    @Column(name = "seal_no")
    private String sealNo;

    @Column(name = "packaging_no")
    private String packagingNo;

    @Column(name = "created_by")
    private String createdBy;

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

    public Integer getWasteMaster() {
        return wasteMaster;
    }

    public void setWasteMaster(Integer wasteMaster) {
        this.wasteMaster = wasteMaster;
    }

    public Integer getWasteGroup() {
        return wasteGroup;
    }

    public void setWasteGroup(Integer wasteGroup) {
        this.wasteGroup = wasteGroup;
    }

    public Integer getWasteType() {
        return wasteType;
    }

    public void setWasteType(Integer wasteType) {
        this.wasteType = wasteType;
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
}
