package pidvn.modules.qa.iqc_recheck.models;

public class LotDto {

    private String requestNo;
    private String model;
    private String lotGroup;
    private String lotNo;
    private String result1;
    private String result2;
    private String result3;
    private String remark;
    private String createdBy;
    private String levelOfControlNgoaiQuan;
    private String levelOfControlKichThuoc;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLevelOfControlNgoaiQuan() {
        return levelOfControlNgoaiQuan;
    }

    public void setLevelOfControlNgoaiQuan(String levelOfControlNgoaiQuan) {
        this.levelOfControlNgoaiQuan = levelOfControlNgoaiQuan;
    }

    public String getLevelOfControlKichThuoc() {
        return levelOfControlKichThuoc;
    }

    public void setLevelOfControlKichThuoc(String levelOfControlKichThuoc) {
        this.levelOfControlKichThuoc = levelOfControlKichThuoc;
    }
}
