package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_overtime_data")
@EntityListeners(AuditingEntityListener.class)
public class HrOvertimeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "ngay_cong")
    private Date ngayCong;
    @Column(name = "register_hours")
    private Float registerHours;
    @Column(name = "confirm_hours")
    private Float confirmHours;
    @Column(name = "status")
    private String status;
    @Column(name = "method_payment")
    private String methodPayment;
    @Column(name = "overtime_type_name")
    private String overtimeTypeName;
    @Column(name = "code_statistic")
    private String codeStatistic;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getNgayCong() {
        return ngayCong;
    }

    public void setNgayCong(Date ngayCong) {
        this.ngayCong = ngayCong;
    }

    public Float getRegisterHours() {
        return registerHours;
    }

    public void setRegisterHours(Float registerHours) {
        this.registerHours = registerHours;
    }

    public Float getConfirmHours() {
        return confirmHours;
    }

    public void setConfirmHours(Float confirmHours) {
        this.confirmHours = confirmHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }

    public String getOvertimeTypeName() {
        return overtimeTypeName;
    }

    public void setOvertimeTypeName(String overtimeTypeName) {
        this.overtimeTypeName = overtimeTypeName;
    }

    public String getCodeStatistic() {
        return codeStatistic;
    }

    public void setCodeStatistic(String codeStatistic) {
        this.codeStatistic = codeStatistic;
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
