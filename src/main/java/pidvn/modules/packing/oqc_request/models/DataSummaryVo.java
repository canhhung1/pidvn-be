package pidvn.modules.packing.oqc_request.models;

public class DataSummaryVo {

    private String lotNo;
    private String model;
    private Float qty;
    private String qaCard;
    private Float ok;
    private Float ng;
    private Float na;

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public Float getOk() {
        return ok;
    }

    public void setOk(Float ok) {
        this.ok = ok;
    }

    public Float getNg() {
        return ng;
    }

    public void setNg(Float ng) {
        this.ng = ng;
    }

    public Float getNa() {
        return na;
    }

    public void setNa(Float na) {
        this.na = na;
    }
}

