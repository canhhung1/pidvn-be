package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "mea_standard")
@EntityListeners(AuditingEntityListener.class)
public class MeaStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item")
    private Integer item;

    @Column(name = "model_type")
    private String modelType;

    @Column(name = "a1_min")
    private Float a1Min;

    @Column(name = "a1_max")
    private Float a1Max;

    @Column(name = "b1_min")
    private Float b1Min;

    @Column(name = "b1_max")
    private Float b1Max;

    @Column(name = "b2_min")
    private Float b2Min;

    @Column(name = "b2_max")
    private Float b2Max;

    @Column(name = "c1_min")
    private Float c1Min;

    @Column(name = "c1_max")
    private Float c1Max;

    @Column(name = "c2_min")
    private Float c2Min;

    @Column(name = "c2_max")
    private Float c2Max;

    @Column(name = "c3_min")
    private Float c3Min;

    @Column(name = "c3_max")
    private Float c3Max;

    @Column(name = "d1_min")
    private Float d1Min;

    @Column(name = "d1_max")
    private Float d1Max;

    @Column(name = "d2_min")
    private Float d2Min;

    @Column(name = "d2_max")
    private Float d2Max;

    @Column(name = "d3_min")
    private Float d3Min;

    @Column(name = "d3_max")
    private Float d3Max;

    @Column(name = "d4_min")
    private Float d4Min;

    @Column(name = "d4_max")
    private Float d4Max;

    @Column(name = "e1_min")
    private Float e1Min;

    @Column(name = "e1_max")
    private Float e1Max;

    @Column(name = "e2_min")
    private Float e2Min;

    @Column(name = "e2_max")
    private Float e2Max;

    @Column(name = "e3_min")
    private Float e3Min;

    @Column(name = "e3_max")
    private Float e3Max;

    @Column(name = "e4_min")
    private Float e4Min;

    @Column(name = "e4_max")
    private Float e4Max;

    @Column(name = "e5_min")
    private Float e5Min;

    @Column(name = "e5_max")
    private Float e5Max;

    @Column(name = "process")
    private String process;

    @Column(name = "tool")
    private String tool;

    @Column(name = "standard")
    private String standard;

    @Column(name = "formula")
    private String formula;

    @Column(name = "unit")
    private String unit;

    @Column(name = "tool_code")
    private String toolCode;

    @Column(name = "mng_code")
    private String mngCode;

    @Column(name = "type")
    private Integer type;

    @Column(name = "value_name")
    private String valueName;

    @Column(name = "measure_amount")
    private Integer measureAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Float getA1Min() {
        return a1Min;
    }

    public void setA1Min(Float a1Min) {
        this.a1Min = a1Min;
    }

    public Float getA1Max() {
        return a1Max;
    }

    public void setA1Max(Float a1Max) {
        this.a1Max = a1Max;
    }

    public Float getB1Min() {
        return b1Min;
    }

    public void setB1Min(Float b1Min) {
        this.b1Min = b1Min;
    }

    public Float getB1Max() {
        return b1Max;
    }

    public void setB1Max(Float b1Max) {
        this.b1Max = b1Max;
    }

    public Float getB2Min() {
        return b2Min;
    }

    public void setB2Min(Float b2Min) {
        this.b2Min = b2Min;
    }

    public Float getB2Max() {
        return b2Max;
    }

    public void setB2Max(Float b2Max) {
        this.b2Max = b2Max;
    }

    public Float getC1Min() {
        return c1Min;
    }

    public void setC1Min(Float c1Min) {
        this.c1Min = c1Min;
    }

    public Float getC1Max() {
        return c1Max;
    }

    public void setC1Max(Float c1Max) {
        this.c1Max = c1Max;
    }

    public Float getC2Min() {
        return c2Min;
    }

    public void setC2Min(Float c2Min) {
        this.c2Min = c2Min;
    }

    public Float getC2Max() {
        return c2Max;
    }

    public void setC2Max(Float c2Max) {
        this.c2Max = c2Max;
    }

    public Float getC3Min() {
        return c3Min;
    }

    public void setC3Min(Float c3Min) {
        this.c3Min = c3Min;
    }

    public Float getC3Max() {
        return c3Max;
    }

    public void setC3Max(Float c3Max) {
        this.c3Max = c3Max;
    }

    public Float getD1Min() {
        return d1Min;
    }

    public void setD1Min(Float d1Min) {
        this.d1Min = d1Min;
    }

    public Float getD1Max() {
        return d1Max;
    }

    public void setD1Max(Float d1Max) {
        this.d1Max = d1Max;
    }

    public Float getD2Min() {
        return d2Min;
    }

    public void setD2Min(Float d2Min) {
        this.d2Min = d2Min;
    }

    public Float getD2Max() {
        return d2Max;
    }

    public void setD2Max(Float d2Max) {
        this.d2Max = d2Max;
    }

    public Float getD3Min() {
        return d3Min;
    }

    public void setD3Min(Float d3Min) {
        this.d3Min = d3Min;
    }

    public Float getD3Max() {
        return d3Max;
    }

    public void setD3Max(Float d3Max) {
        this.d3Max = d3Max;
    }

    public Float getD4Min() {
        return d4Min;
    }

    public void setD4Min(Float d4Min) {
        this.d4Min = d4Min;
    }

    public Float getD4Max() {
        return d4Max;
    }

    public void setD4Max(Float d4Max) {
        this.d4Max = d4Max;
    }

    public Float getE1Min() {
        return e1Min;
    }

    public void setE1Min(Float e1Min) {
        this.e1Min = e1Min;
    }

    public Float getE1Max() {
        return e1Max;
    }

    public void setE1Max(Float e1Max) {
        this.e1Max = e1Max;
    }

    public Float getE2Min() {
        return e2Min;
    }

    public void setE2Min(Float e2Min) {
        this.e2Min = e2Min;
    }

    public Float getE2Max() {
        return e2Max;
    }

    public void setE2Max(Float e2Max) {
        this.e2Max = e2Max;
    }

    public Float getE3Min() {
        return e3Min;
    }

    public void setE3Min(Float e3Min) {
        this.e3Min = e3Min;
    }

    public Float getE3Max() {
        return e3Max;
    }

    public void setE3Max(Float e3Max) {
        this.e3Max = e3Max;
    }

    public Float getE4Min() {
        return e4Min;
    }

    public void setE4Min(Float e4Min) {
        this.e4Min = e4Min;
    }

    public Float getE4Max() {
        return e4Max;
    }

    public void setE4Max(Float e4Max) {
        this.e4Max = e4Max;
    }

    public Float getE5Min() {
        return e5Min;
    }

    public void setE5Min(Float e5Min) {
        this.e5Min = e5Min;
    }

    public Float getE5Max() {
        return e5Max;
    }

    public void setE5Max(Float e5Max) {
        this.e5Max = e5Max;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getMngCode() {
        return mngCode;
    }

    public void setMngCode(String mngCode) {
        this.mngCode = mngCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Integer getMeasureAmount() {
        return measureAmount;
    }

    public void setMeasureAmount(Integer measureAmount) {
        this.measureAmount = measureAmount;
    }
}
