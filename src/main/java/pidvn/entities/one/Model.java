package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "model")
@EntityListeners(AuditingEntityListener.class)
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "product_type_id")
    private Integer productTypeId;

    @Column(name = "image")
    private String image;

    @Column(name = "classified")
    private String classified;

    @Column(name = "standard_packing_qty")
    private Integer standardPackingQty;

    @Column(name = "std_packing2")
    private Integer stdPacking2;

    @Column(name = "assemble_part")
    private String assemblePart;

    @Column(name = "assemble_part2")
    private String assemblePart2;

    @Column(name = "assemble_type")
    private String assembleType;

    @Column(name = "assemble_type2")
    private String assembleType2;

    @Column(name = "line")
    private String line;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "unit")
    private String unit;

    @Column(name = "label_type")
    private String labelType;

    @Column(name = "label_color")
    private String labelColor;

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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public Integer getStandardPackingQty() {
        return standardPackingQty;
    }

    public void setStandardPackingQty(Integer standardPackingQty) {
        this.standardPackingQty = standardPackingQty;
    }

    public Integer getStdPacking2() {
        return stdPacking2;
    }

    public void setStdPacking2(Integer stdPacking2) {
        this.stdPacking2 = stdPacking2;
    }

    public String getAssemblePart() {
        return assemblePart;
    }

    public void setAssemblePart(String assemblePart) {
        this.assemblePart = assemblePart;
    }

    public String getAssemblePart2() {
        return assemblePart2;
    }

    public void setAssemblePart2(String assemblePart2) {
        this.assemblePart2 = assemblePart2;
    }

    public String getAssembleType() {
        return assembleType;
    }

    public void setAssembleType(String assembleType) {
        this.assembleType = assembleType;
    }

    public String getAssembleType2() {
        return assembleType2;
    }

    public void setAssembleType2(String assembleType2) {
        this.assembleType2 = assembleType2;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

}
