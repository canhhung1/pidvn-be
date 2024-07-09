package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "ie_project_progress")
@EntityListeners(AuditingEntityListener.class)
public class IeProjectProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


}
