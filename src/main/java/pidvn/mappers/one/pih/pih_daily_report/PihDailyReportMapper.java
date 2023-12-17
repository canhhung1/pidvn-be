package pidvn.mappers.one.pih.pih_daily_report;

import org.apache.ibatis.annotations.Param;
import pidvn.modules.pih.pih_daily_report.models.DailyReportDataVo;

import java.util.Date;
import java.util.List;

public interface PihDailyReportMapper {
    List<DailyReportDataVo> getDailyReportData(@Param("date") Date date);
}
