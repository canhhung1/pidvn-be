package pidvn.modules.relay.training_record.models;

import java.util.Date;

public class TrainingRecordMasterVo {

    private Integer id;
    private Integer course;
    private String courseName;
    private Integer courseType;
    private String courseTypeName;
    private Float scoreOfPass;
    private Float scoreOfMax;
    private String trainer;
    private String trainerName;
    private String approvalBy;
    private String approvalByName;
    private Date date;
    private Date createdAt;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
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

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
    }

    public String getApprovalByName() {
        return approvalByName;
    }

    public void setApprovalByName(String approvalByName) {
        this.approvalByName = approvalByName;
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
}
