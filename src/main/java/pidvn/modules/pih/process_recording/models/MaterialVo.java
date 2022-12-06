package pidvn.modules.pih.process_recording.models;


import java.util.Date;

public class MaterialVo {

    private Integer id;
    private String ppn;
    private String cpn;
    private String plotno;
    private String clotno;
    private Integer frBox;
    private Integer toBox;
    private Integer windingBobbin;
    private Float qty;
    private Date date;
    private String line;
    private String employee;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getCpn() {
        return cpn;
    }

    public void setCpn(String cpn) {
        this.cpn = cpn;
    }

    public String getPlotno() {
        return plotno;
    }

    public void setPlotno(String plotno) {
        this.plotno = plotno;
    }

    public String getClotno() {
        return clotno;
    }

    public void setClotno(String clotno) {
        this.clotno = clotno;
    }

    public Integer getFrBox() {
        return frBox;
    }

    public void setFrBox(Integer frBox) {
        this.frBox = frBox;
    }

    public Integer getToBox() {
        return toBox;
    }

    public void setToBox(Integer toBox) {
        this.toBox = toBox;
    }

    public Integer getWindingBobbin() {
        return windingBobbin;
    }

    public void setWindingBobbin(Integer windingBobbin) {
        this.windingBobbin = windingBobbin;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


}
