package pidvn.modules.qa.qa_training_matrix.models;

public class CourseVo {
    private Integer id;
    private String courseName;
    private String groupCourse;
    private String groupTraining;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
