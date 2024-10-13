package pidvn.modules.warehouse.iqc_recheck.models;

public class LotDto {
    private String model;
    private String lotGroup;
    private String lotNo;
    private Integer amount;
    private String classified;
    private String shift;
    private String line;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLotGroup() {
        return lotGroup;
    }

    public void setLotGroup(String lotGroup) {
        this.lotGroup = lotGroup;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
