package pidvn.entities.one;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_leaveday")
@EntityListeners(AuditingEntityListener.class)
public class HrLeaveDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_emp")
    private String codeEmp;

    @Column(name = "code")
    private String code;

    @Column(name = "leave_day_type_name")
    private String leaveDayTypeName;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "leave_days")
    private Float leaveDays;

    @Column(name = "leave_hours")
    private Float leaveHours;

    @Column(name = "date_create")
    private Date dateCreate;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @CreatedDate
    @Column(name = "updated_at")
    private Date updatedAt;
}
