package pidvn.modules.relay.vr_enc_process_recording.models;

import java.util.Date;

public class PartVo {
    private Integer id;
    private String pnpa;
    private String pncomp;
    private String qtpern;
    private Integer qtperd;
    private String umprqt;
    private String tpstco;
    private String cdfc;
    private Double stdCost;
    private Date createdAt;
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

    public String getQtpern() {
        return qtpern;
    }

    public void setQtpern(String qtpern) {
        this.qtpern = qtpern;
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
