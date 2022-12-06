package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "so_cccd")
    private String soCccd;

    @Column(name = "ngaycap_cccd")
    private Date ngaycapCccd;

    @Column(name = "noicap_cccd")
    private String noicapCccd;

    @Column(name = "so_passport")
    private String soPassport;

    @Column(name = "ngaycap_passport")
    private Date ngaycapPassport;

    @Column(name = "ngayhethan_passport")
    private Date ngayhethanPassport;

    @Column(name = "noicap_passport")
    private String noicapPassport;

    @Column(name = "so_cmnd")
    private String soCmnd;

    @Column(name = "ngaycap_tcc")
    private Date ngaycapTcc;

    @Column(name = "ngayhethan_tcc")
    private Date ngayhethanTcc;

    @Column(name = "noicap_tcc")
    private String noicapTcc;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "phone")
    private String phone;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "zalo")
    private String zalo;

    @Column(name = "diachi_thuongtru")
    private String diachiThuongtru;

    @Column(name = "diachi_tamtru")
    private String diachiTamtru;

    @Column(name = "noisinh")
    private String noisinh;

    @Column(name = "nguyenquan")
    private String nguyenquan;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "dantoc")
    private String dantoc;

    @Column(name = "tongiao")
    private String tongiao;

    @Column(name = "trinhdo_vanhoa")
    private String trinhdoVanhoa;

    @Column(name = "trinhdo_hocvan")
    private String trinhdoHocvan;

    @Column(name = "email")
    private String email;

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

    public String getSoCccd() {
        return soCccd;
    }

    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }

    public Date getNgaycapCccd() {
        return ngaycapCccd;
    }

    public void setNgaycapCccd(Date ngaycapCccd) {
        this.ngaycapCccd = ngaycapCccd;
    }

    public String getNoicapCccd() {
        return noicapCccd;
    }

    public void setNoicapCccd(String noicapCccd) {
        this.noicapCccd = noicapCccd;
    }

    public String getSoPassport() {
        return soPassport;
    }

    public void setSoPassport(String soPassport) {
        this.soPassport = soPassport;
    }

    public Date getNgaycapPassport() {
        return ngaycapPassport;
    }

    public void setNgaycapPassport(Date ngaycapPassport) {
        this.ngaycapPassport = ngaycapPassport;
    }

    public Date getNgayhethanPassport() {
        return ngayhethanPassport;
    }

    public void setNgayhethanPassport(Date ngayhethanPassport) {
        this.ngayhethanPassport = ngayhethanPassport;
    }

    public String getNoicapPassport() {
        return noicapPassport;
    }

    public void setNoicapPassport(String noicapPassport) {
        this.noicapPassport = noicapPassport;
    }

    public String getSoCmnd() {
        return soCmnd;
    }

    public void setSoCmnd(String soCmnd) {
        this.soCmnd = soCmnd;
    }

    public Date getNgaycapTcc() {
        return ngaycapTcc;
    }

    public void setNgaycapTcc(Date ngaycapTcc) {
        this.ngaycapTcc = ngaycapTcc;
    }

    public Date getNgayhethanTcc() {
        return ngayhethanTcc;
    }

    public void setNgayhethanTcc(Date ngayhethanTcc) {
        this.ngayhethanTcc = ngayhethanTcc;
    }

    public String getNoicapTcc() {
        return noicapTcc;
    }

    public void setNoicapTcc(String noicapTcc) {
        this.noicapTcc = noicapTcc;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public String getDiachiThuongtru() {
        return diachiThuongtru;
    }

    public void setDiachiThuongtru(String diachiThuongtru) {
        this.diachiThuongtru = diachiThuongtru;
    }

    public String getDiachiTamtru() {
        return diachiTamtru;
    }

    public void setDiachiTamtru(String diachiTamtru) {
        this.diachiTamtru = diachiTamtru;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getNguyenquan() {
        return nguyenquan;
    }

    public void setNguyenquan(String nguyenquan) {
        this.nguyenquan = nguyenquan;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDantoc() {
        return dantoc;
    }

    public void setDantoc(String dantoc) {
        this.dantoc = dantoc;
    }

    public String getTongiao() {
        return tongiao;
    }

    public void setTongiao(String tongiao) {
        this.tongiao = tongiao;
    }

    public String getTrinhdoVanhoa() {
        return trinhdoVanhoa;
    }

    public void setTrinhdoVanhoa(String trinhdoVanhoa) {
        this.trinhdoVanhoa = trinhdoVanhoa;
    }

    public String getTrinhdoHocvan() {
        return trinhdoHocvan;
    }

    public void setTrinhdoHocvan(String trinhdoHocvan) {
        this.trinhdoHocvan = trinhdoHocvan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
