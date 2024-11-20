package pidvn.entities.one;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_leaveday")
@EntityListeners(AuditingEntityListener.class)
public class HrLeaveDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_emp")
    private String codeEmp;

    @Column(name = "code")
    private String code;

    @Column(name = "leave_day_type_name")
    private String leaveDayTypeName;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "leave_days")
    private Float leaveDays;

    @Column(name = "leave_hours")
    private Float leaveHours;

    @Column(name = "duration_type")
    private String durationType;

    @Column(name = "status")
    private String status;

    @Column(name = "date_create")
    private Date dateCreate;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @CreatedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeaveDayTypeName() {
        return leaveDayTypeName;
    }

    public void setLeaveDayTypeName(String leaveDayTypeName) {
        this.leaveDayTypeName = leaveDayTypeName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Float getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Float leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Float getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(Float leaveHours) {
        this.leaveHours = leaveHours;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
