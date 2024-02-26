package pidvn.modules.qa.qa_training_matrix.models;

public class RowExcelErrorVo {

    private Integer rowNum;

    private String error;

    public RowExcelErrorVo() {
    }

    public RowExcelErrorVo(Integer rowNum, String error) {
        this.rowNum = rowNum;
        this.error = error;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
