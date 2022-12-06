package pidvn.entities.one;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "waste_detail_image")
@EntityListeners(AuditingEntityListener.class)
public class WasteDetailImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "waste_detail")
    private Integer wasteDetail;

    @Column(name = "waste_image")
    private Integer wasteImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWasteDetail() {
        return wasteDetail;
    }

    public void setWasteDetail(Integer wasteDetail) {
        this.wasteDetail = wasteDetail;
    }

    public Integer getWasteImage() {
        return wasteImage;
    }

    public void setWasteImage(Integer wasteImage) {
        this.wasteImage = wasteImage;
    }
}
