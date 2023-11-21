package pidvn.modules.relay.vr_enc_process_recording.models;

import java.util.Date;
import java.util.List;

public class QaCardVo {

    private Integer id;
    private String model;
    private String line;
    private String lineCode;
    private Date date;
    private String shift;
    private String shiftCode;
    private String lotNo;
    private String userCode;
    private String remark;
    private List<String> customers;
    private String customerCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
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

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
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

    public List<String> getCustomers() {
        return customers;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public String toString() {
        return "QaCardVo{" +
                "model='" + model + '\'' +
                ", line='" + line + '\'' +
                ", lineCode='" + lineCode + '\'' +
                ", date=" + date +
                ", shift='" + shift + '\'' +
                ", shiftCode='" + shiftCode + '\'' +
                ", lotNo='" + lotNo + '\'' +
                ", userCode='" + userCode + '\'' +
                ", remark='" + remark + '\'' +
                ", customers=" + customers +
                '}';
    }
}
