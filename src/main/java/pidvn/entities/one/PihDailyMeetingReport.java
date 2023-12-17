package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pih_daily_meeting_report")
@EntityListeners(AuditingEntityListener.class)
public class PihDailyMeetingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "line")
    private String line;
    @Column(name = "date")
    private Date date;
    @Column(name = "report_date")
    private Date reportDate;
    @Column(name = "target_qty")
    private Integer targetQty;
    @Column(name = "actual_qty")
    private Integer actualQty;
    @Column(name = "die_a_qty")
    private Integer dieAQty;
    @Column(name = "die_b_qty")
    private Integer dieBQty;
    @Column(name = "remark")
    private String remark;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getTargetQty() {
        return targetQty;
    }

    public void setTargetQty(Integer targetQty) {
        this.targetQty = targetQty;
    }

    public Integer getActualQty() {
        return actualQty;
    }

    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public Integer getDieAQty() {
        return dieAQty;
    }

    public void setDieAQty(Integer dieAQty) {
        this.dieAQty = dieAQty;
    }

    public Integer getDieBQty() {
        return dieBQty;
    }

    public void setDieBQty(Integer dieBQty) {
        this.dieBQty = dieBQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
