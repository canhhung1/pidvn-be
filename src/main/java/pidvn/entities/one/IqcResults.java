package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "iqc_results")
@EntityListeners(AuditingEntityListener.class)
public class IqcResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "class")
    private String classCol;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "line")
    private String line;

    @Column(name = "model")
    private String model;

    @Column(name = "shift")
    private String shift;

    @Column(name = "p_date")
    private Date pDate;

    @Column(name = "date")
    private Date date;

    @Column(name = "result1")
    private String result1;

    @Column(name = "result2")
    private String result2;

    @Column(name = "check_result")
    private String checkResult;

    @Column(name = "check_result2")
    private String checkResult2;

    @Column(name = "check_user")
    private String checkUser;

    @Column(name = "check_date")
    private String checkDate;

    @Column(name = "check_remark")
    private String checkRemark;

    @Column(name = "remark")
    private String remark;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "key_in_id")
    private Integer keyInId;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "request_no")
    private String requestNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCol() {
        return classCol;
    }

    public void setClassCol(String classCol) {
        this.classCol = classCol;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckResult2() {
        return checkResult2;
    }

    public void setCheckResult2(String checkResult2) {
        this.checkResult2 = checkResult2;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getKeyInId() {
        return keyInId;
    }

    public void setKeyInId(Integer keyInId) {
        this.keyInId = keyInId;
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

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
