package pidvn.modules.qa.oqc_check.models;

public class OqcDataVo {

    private Integer id;
    private Integer checkItemId;
    private String viName;
    private String enName;
    private String reqNo;
    private String qaCard;
    private String value;
    private Integer numberOfSampling;
    private Integer rejectNumber;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(Integer checkItemId) {
        this.checkItemId = checkItemId;
    }

    public String getViName() {
        return viName;
    }

    public void setViName(String viName) {
        this.viName = viName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getQaCard() {
        return qaCard;
    }

    public void setQaCard(String qaCard) {
        this.qaCard = qaCard;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumberOfSampling() {
        return numberOfSampling;
    }

    public void setNumberOfSampling(Integer numberOfSampling) {
        this.numberOfSampling = numberOfSampling;
    }

    public Integer getRejectNumber() {
        return rejectNumber;
    }

    public void setRejectNumber(Integer rejectNumber) {
        this.rejectNumber = rejectNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
