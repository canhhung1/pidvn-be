package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "ie_drawing")
@EntityListeners(AuditingEntityListener.class)
public class IeDrawing {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "drawing_no")
    private String drawingNo;
    @Column(name = "drawing_name")
    private String drawingName;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "unit")
    private String unit;
    @Column(name = "material")
    private String material;
    @Column(name = "hardness")
    private String hardness;
    @Column(name = "polishing")
    private String polishing;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "version")
    private String version;
    @Column(name = "remark")
    private String remark;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "progress_id")
    private Integer progressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDrawingNo() {
        return drawingNo;
    }

    public void setDrawingNo(String drawingNo) {
        this.drawingNo = drawingNo;
    }

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getPolishing() {
        return polishing;
    }

    public void setPolishing(String polishing) {
        this.polishing = polishing;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }
}
