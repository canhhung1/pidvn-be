package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "material_controls")
@EntityListeners(AuditingEntityListener.class)
public class MaterialControls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ppn")
    private String ppn;

    @Column(name = "cpn")
    private String cpn;

    @Column(name = "line")
    private String line;

    @Column(name = "date")
    private Date date;

    @Column(name = "shift")
    private String shift;

    @Column(name = "plotno")
    private String plotno;

    @Column(name = "clotno")
    private String clotno;

    @Column(name = "qty")
    private Float qty;

    @Column(name = "fr_box")
    private Integer frBox;

    @Column(name = "to_box")
    private Integer toBox;

    @Column(name = "def1")
    private Integer def1;

    @Column(name = "def2")
    private Integer def2;

    @Column(name = "def3")
    private Integer def3;

    @Column(name = "user1")
    private String user1;

    @Column(name = "user2")
    private String user2;

    @Column(name = "key_user")
    private String keyUser;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "winding_bobbin")
    private Integer windingBobbin;

    @Column(name = "scan_no")
    private String scanNo;

    @Column(name = "ng_qty")
    private Integer ngQty;

    @Column(name = "remark")
    private String remark;

    @Column(name = "record_type")
    private String recordType;

    @Column(name = "process_id")
    private Integer processId;

    @Column(name = "ordinal")
    private Integer ordinal;

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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
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

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
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

    public Integer getDef1() {
        return def1;
    }

    public void setDef1(Integer def1) {
        this.def1 = def1;
    }

    public Integer getDef2() {
        return def2;
    }

    public void setDef2(Integer def2) {
        this.def2 = def2;
    }

    public Integer getDef3() {
        return def3;
    }

    public void setDef3(Integer def3) {
        this.def3 = def3;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
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

    public Integer getWindingBobbin() {
        return windingBobbin;
    }

    public void setWindingBobbin(Integer windingBobbin) {
        this.windingBobbin = windingBobbin;
    }

    public String getScanNo() {
        return scanNo;
    }

    public void setScanNo(String scanNo) {
        this.scanNo = scanNo;
    }

    public Integer getNgQty() {
        return ngQty;
    }

    public void setNgQty(Integer ngQty) {
        this.ngQty = ngQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
