package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "qa_training_matrix_course")
@EntityListeners(AuditingEntityListener.class)
public class QaTrainingMatrixCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_group_course")
    private Integer subGroupCourse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubGroupCourse() {
        return subGroupCourse;
    }

    public void setSubGroupCourse(Integer subGroupCourse) {
        this.subGroupCourse = subGroupCourse;
    }
}
