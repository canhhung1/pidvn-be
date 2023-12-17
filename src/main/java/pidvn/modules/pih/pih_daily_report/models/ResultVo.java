package pidvn.modules.pih.pih_daily_report.models;

import java.util.Date;
import java.util.List;

public class ResultVo {
    private String line;
    private Date reportDate;
    private Integer targetQty;
    private Integer actualQty;
    private Integer dieAQty;
    private Integer dieBQty;
    private String remark;

    private List<DailyReportDataVo> dataSource;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<DailyReportDataVo> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<DailyReportDataVo> dataSource) {
        this.dataSource = dataSource;
    }
}
