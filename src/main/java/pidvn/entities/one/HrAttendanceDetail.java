package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_attendance_detail")
@EntityListeners(AuditingEntityListener.class)
public class HrAttendanceDetail {

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
    @Column(name = "ca_lam_viec")
    private String caLamViec;
    @Column(name = "first_in_time")
    private Date firstInTime;
    @Column(name = "last_out_time")
    private Date lastOutTime;
    @Column(name = "trang_thai_tinh_cong")
    private String trangThaiTinhCong;
    @Column(name = "leave_code")
    private String leaveCode;
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

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public Date getFirstInTime() {
        return firstInTime;
    }

    public void setFirstInTime(Date firstInTime) {
        this.firstInTime = firstInTime;
    }

    public Date getLastOutTime() {
        return lastOutTime;
    }

    public void setLastOutTime(Date lastOutTime) {
        this.lastOutTime = lastOutTime;
    }

    public String getTrangThaiTinhCong() {
        return trangThaiTinhCong;
    }

    public void setTrangThaiTinhCong(String trangThaiTinhCong) {
        this.trangThaiTinhCong = trangThaiTinhCong;
    }

    public String getLeaveCode() {
        return leaveCode;
    }

    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
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
