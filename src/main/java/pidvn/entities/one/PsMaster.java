package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ps_masters")
@EntityListeners(AuditingEntityListener.class)
public class PsMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pnpa")
    private String pnpa;

    @Column(name = "pncomp")
    private String pncomp;

    @Column(name = "qtperd")
    private Integer qtperd;

    @Column(name = "umprqt")
    private String umprqt;

    @Column(name = "tpstco")
    private String tpstco;

    @Column(name = "cdfc")
    private String cdfc;

    @Column(name = "std_cost")
    private Double stdCost;

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

    public String getPnpa() {
        return pnpa;
    }

    public void setPnpa(String pnpa) {
        this.pnpa = pnpa;
    }

    public String getPncomp() {
        return pncomp;
    }

    public void setPncomp(String pncomp) {
        this.pncomp = pncomp;
    }

    public Integer getQtperd() {
        return qtperd;
    }

    public void setQtperd(Integer qtperd) {
        this.qtperd = qtperd;
    }

    public String getUmprqt() {
        return umprqt;
    }

    public void setUmprqt(String umprqt) {
        this.umprqt = umprqt;
    }

    public String getTpstco() {
        return tpstco;
    }

    public void setTpstco(String tpstco) {
        this.tpstco = tpstco;
    }

    public String getCdfc() {
        return cdfc;
    }

    public void setCdfc(String cdfc) {
        this.cdfc = cdfc;
    }

    public Double getStdCost() {
        return stdCost;
    }

    public void setStdCost(Double stdCost) {
        this.stdCost = stdCost;
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
