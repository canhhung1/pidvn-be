package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "waste_type")
@EntityListeners(AuditingEntityListener.class)
public class WasteType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "waste_group")
    private Integer wasteGroup;

    @Column(name = "unit")
    private String unit;

    @Column(name = "handle_method")
    private Integer handleMethod;

    @Column(name = "waste_status")
    private Integer wasteStatus;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWasteGroup() {
        return wasteGroup;
    }

    public void setWasteGroup(Integer wasteGroup) {
        this.wasteGroup = wasteGroup;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(Integer handleMethod) {
        this.handleMethod = handleMethod;
    }

    public Integer getWasteStatus() {
        return wasteStatus;
    }

    public void setWasteStatus(Integer wasteStatus) {
        this.wasteStatus = wasteStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
