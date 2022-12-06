package pidvn.modules.relay.material_management.models;

import java.util.List;

public class MaterialSearchVo {

    private List<String> recordType;

    private String qaCard;

    public List<String> getRecordType() {
        return recordType;
    }

    public void setRecordType(List<String> recordType) {
        this.recordType = recordType;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }
}
