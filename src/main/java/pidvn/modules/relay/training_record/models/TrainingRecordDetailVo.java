package pidvn.modules.relay.training_record.models;

public class TrainingRecordDetailVo {

    private Integer id;
    private Integer trainingRecordMaster;
    private String username;
    private String positionName;
    private String subsectionName;
    private String attendance;
    private String materialReceived;
    private String score;
    private String testResult;
    private String line;
    private String remark;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainingRecordMaster() {
        return trainingRecordMaster;
    }

    public void setTrainingRecordMaster(Integer trainingRecordMaster) {
        this.trainingRecordMaster = trainingRecordMaster;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getMaterialReceived() {
        return materialReceived;
    }

    public void setMaterialReceived(String materialReceived) {
        this.materialReceived = materialReceived;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
