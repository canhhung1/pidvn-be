package pidvn.modules.qa.qa_training_matrix.models;

import java.util.Date;

public class TrainingRecordVo {

    private String username;
    private String fullName;
    private String position;
    private String section;
    private Integer courseId;
    private String courseName;
    private Date trainingDate;
    private String trainingType;
    private String trainingStatus;
    private String groupCourse;
    private String groupTraining;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public String getGroupCourse() {
        return groupCourse;
    }

    public void setGroupCourse(String groupCourse) {
        this.groupCourse = groupCourse;
    }

    public String getGroupTraining() {
        return groupTraining;
    }

    public void setGroupTraining(String groupTraining) {
        this.groupTraining = groupTraining;
    }
}
