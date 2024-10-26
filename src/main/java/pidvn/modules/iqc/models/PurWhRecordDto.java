package pidvn.modules.iqc.models;

import java.util.Date;

public class PurWhRecordDto {

    private Integer id;
    private String classParam;
    private String type;
    private String model;
    private String lotGroup;
    private String lotNo;
    private Float qty;
    private Date pDate;
    private Date date;
    private String slipNo;
    private String invoice;
    private String result1;
    private String result2;
    private String result3;
    private Integer userId;
    private String requestNo;
    private Integer keyInId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassParam() {
        return classParam;
    }

    public void setClassParam(String classParam) {
        this.classParam = classParam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Integer getKeyInId() {
        return keyInId;
    }

    public void setKeyInId(Integer keyInId) {
        this.keyInId = keyInId;
    }
}
