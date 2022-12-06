package pidvn.entities.one;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_record_master")
@EntityListeners(AuditingEntityListener.class)
public class TrainingRecordMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "course")
    private Integer course;

    @Column(name = "trainer")
    private String trainer;

    @Column(name = "date")
    private Date date;

    @Column(name = "score_of_pass")
    private Float scoreOfPass;

    @Column(name = "score_of_max")
    private Float scoreOfMax;

    @Column(name = "approval_by")
    private String approvalBy;

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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getScoreOfPass() {
        return scoreOfPass;
    }

    public void setScoreOfPass(Float scoreOfPass) {
        this.scoreOfPass = scoreOfPass;
    }

    public Float getScoreOfMax() {
        return scoreOfMax;
    }

    public void setScoreOfMax(Float scoreOfMax) {
        this.scoreOfMax = scoreOfMax;
    }

    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
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
