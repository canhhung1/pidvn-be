package pidvn.modules.pih.process_recording.models;

import java.util.List;

public class ScannerVo {

    private String userId;
    private String label;
    private String newCoil;
    private String oldCoil;
    private Integer qty;
    private Integer sequence;
    private String newLabel;
    private String oldLabel;
    private List<String> lots;
    private Integer position;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNewCoil() {
        return newCoil;
    }

    public void setNewCoil(String newCoil) {
        this.newCoil = newCoil;
    }

    public String getOldCoil() {
        return oldCoil;
    }

    public void setOldCoil(String oldCoil) {
        this.oldCoil = oldCoil;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getNewLabel() {
        return newLabel;
    }

    public void setNewLabel(String newLabel) {
        this.newLabel = newLabel;
    }

    public String getOldLabel() {
        return oldLabel;
    }

    public void setOldLabel(String oldLabel) {
        this.oldLabel = oldLabel;
    }

    public List<String> getLots() {
        return lots;
    }

    public void setLots(List<String> lots) {
        this.lots = lots;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
