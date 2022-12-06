package pidvn.modules.relay.material_control.models;

import java.util.Date;

public class QaCardVo {
    private String qaCard;
    private Date createdAt;

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
