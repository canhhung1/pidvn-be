package pidvn.modules.relay.material_management.relay_warehouse.models;

import java.util.List;

public class ReWhRecordSearchVo {

    private String lotNo;
    private List<String> lotNos;
    private String recordType;
    private List<String> recordTypes;
    private String slipNo;
    private String qaCard;

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public List<String> getLotNos() {
        return lotNos;
    }

    public void setLotNos(List<String> lotNos) {
        this.lotNos = lotNos;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public List<String> getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(List<String> recordTypes) {
        this.recordTypes = recordTypes;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }
}
