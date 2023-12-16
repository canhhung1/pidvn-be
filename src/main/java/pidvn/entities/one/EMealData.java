package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_meal_data")
@EntityListeners(AuditingEntityListener.class)
public class EMealData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "ve_chinh_timesheet")
    private Integer veChinhTimesheet;
    @Column(name = "ve_chinh_actual")
    private Integer veChinhActual;
    @Column(name = "ve_phu_timesheet")
    private Integer vePhuTimesheet;
    @Column(name = "ve_phu_actual")
    private Integer vePhuActual;
    @Column(name = "ve_phu_tro_cap")
    private Integer vePhuTroCap;
    @Column(name = "date")
    private Date date;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getVeChinhTimesheet() {
        return veChinhTimesheet;
    }

    public void setVeChinhTimesheet(Integer veChinhTimesheet) {
        this.veChinhTimesheet = veChinhTimesheet;
    }

    public Integer getVeChinhActual() {
        return veChinhActual;
    }

    public void setVeChinhActual(Integer veChinhActual) {
        this.veChinhActual = veChinhActual;
    }

    public Integer getVePhuTimesheet() {
        return vePhuTimesheet;
    }

    public void setVePhuTimesheet(Integer vePhuTimesheet) {
        this.vePhuTimesheet = vePhuTimesheet;
    }

    public Integer getVePhuActual() {
        return vePhuActual;
    }

    public void setVePhuActual(Integer vePhuActual) {
        this.vePhuActual = vePhuActual;
    }

    public Integer getVePhuTroCap() {
        return vePhuTroCap;
    }

    public void setVePhuTroCap(Integer vePhuTroCap) {
        this.vePhuTroCap = vePhuTroCap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
