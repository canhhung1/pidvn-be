package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_record_detail")
@EntityListeners(AuditingEntityListener.class)
public class TrainingRecordDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "training_record_master")
    private Integer trainingRecordMaster;

    @Column(name = "username")
    private String username;

    @Column(name = "attendance")
    private String attendance;

    @Column(name = "material_received")
    private String materialReceived;

    @Column(name = "score")
    private Integer score;

    @Column(name = "remark")
    private String remark;

    @Column(name = "test_result")
    private String testResult;

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

    public Integer getTrainingRecordMaster() {
        return trainingRecordMaster;
    }

    public void setTrainingRecordMaster(Integer trainingRecordMaster) {
        this.trainingRecordMaster = trainingRecordMaster;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getMaterialReceived() {
        return materialReceived;
    }

    public void setMaterialReceived(String materialReceived) {
        this.materialReceived = materialReceived;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
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
