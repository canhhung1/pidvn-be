package pidvn.modules.pih.pih_daily_report.services;

import pidvn.entities.one.PihDailyMeetingReport;
import pidvn.modules.pih.pih_daily_report.models.DailyReportDataVo;
import pidvn.modules.pih.pih_daily_report.models.ResultVo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IPihDailyReportSvc {
    List<ResultVo> getDailyReportData(Date date);
    PihDailyMeetingReport saveDailyReportData(PihDailyMeetingReport data);
}
