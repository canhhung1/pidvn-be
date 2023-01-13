package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_courses")
@EntityListeners(AuditingEntityListener.class)
public class HrCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_course_groups")
    private String idCourseGroups;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "time_start")
    private Date timeStart;

    @Column(name = "date_finish")
    private Date dateFinish;

    @Column(name = "time_finish")
    private Date timeFinish;

    @Column(name = "method")
    private String method;

    @Column(name = "place")
    private String place;

    @Column(name = "teacher")
    private String teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCourseGroups() {
        return idCourseGroups;
    }

    public void setIdCourseGroups(String idCourseGroups) {
        this.idCourseGroups = idCourseGroups;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
