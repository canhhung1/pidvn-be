package pidvn.modules.relay.checksheet.models;

public class ProcessVo {

    private Integer id;
    private String name;
    private String evaluate;
    private Integer total;
    private Integer checkedAmount;
    private String models;
    private Integer checkSheetGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Integer checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public Integer getCheckSheetGroup() {
        return checkSheetGroup;
    }

    public void setCheckSheetGroup(Integer checkSheetGroup) {
        this.checkSheetGroup = checkSheetGroup;
    }
}
