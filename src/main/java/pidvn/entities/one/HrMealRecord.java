package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_meal_record")
@EntityListeners(AuditingEntityListener.class)
public class HrMealRecord {

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
    @Column(name = "time_log")
    private Date timeLog;
    @Column(name = "machine_code")
    private String machineCode;
    @Column(name = "amount")
    private String amount;
    @Column(name = "canteen_code")
    private String canteenCode;
    @Column(name = "canteen_name")
    private String canteenName;
    @Column(name = "catering_code")
    private String cateringCode;
    @Column(name = "catering_name")
    private String cateringName;
    @Column(name = "line_code")
    private String lineCode;
    @Column(name = "line_name")
    private String lineName;

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

    public Date getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(Date timeLog) {
        this.timeLog = timeLog;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public void setCanteenCode(String canteenCode) {
        this.canteenCode = canteenCode;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getCateringCode() {
        return cateringCode;
    }

    public void setCateringCode(String cateringCode) {
        this.cateringCode = cateringCode;
    }

    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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
