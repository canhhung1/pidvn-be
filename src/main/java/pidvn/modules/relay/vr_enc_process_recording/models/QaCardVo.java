package pidvn.modules.relay.vr_enc_process_recording.models;

import java.util.Date;

public class QaCardVo {

    private String model;
    private String line;
    private Date date;
    private String shift;
    private String lotNo;
    private String userCode;
    private String remark;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "QaCardVo{" +
                "model='" + model + '\'' +
                ", line='" + line + '\'' +
                ", date=" + date +
                ", shift='" + shift + '\'' +
                ", lotNo='" + lotNo + '\'' +
                '}';
    }
}
