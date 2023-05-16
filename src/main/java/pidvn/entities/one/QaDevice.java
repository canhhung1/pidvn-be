package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "qa_device")
@EntityListeners(AuditingEntityListener.class)
public class QaDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "control_no")
    private String controlNo;
    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "serial_no")
    private String serialNo;
    @Column(name = "type_no")
    private String typeNo;
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "range_of_measuring")
    private String rangeOfMeasuring;
    @Column(name = "distribution_location")
    private String distributionLocation;

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

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRangeOfMeasuring() {
        return rangeOfMeasuring;
    }

    public void setRangeOfMeasuring(String rangeOfMeasuring) {
        this.rangeOfMeasuring = rangeOfMeasuring;
    }

    public String getDistributionLocation() {
        return distributionLocation;
    }

    public void setDistributionLocation(String distributionLocation) {
        this.distributionLocation = distributionLocation;
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
