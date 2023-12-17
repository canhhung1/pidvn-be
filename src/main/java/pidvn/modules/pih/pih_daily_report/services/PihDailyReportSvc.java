package pidvn.modules.pih.pih_daily_report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.PihDailyMeetingReport;
import pidvn.mappers.one.pih.pih_daily_report.PihDailyReportMapper;
import pidvn.modules.pih.pih_daily_report.models.DailyReportDataVo;
import pidvn.modules.pih.pih_daily_report.models.ResultVo;
import pidvn.repositories.one.PihDailyMeetingReportRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PihDailyReportSvc implements IPihDailyReportSvc {

    @Autowired
    private PihDailyReportMapper pihDailyReportMapper;

    @Autowired
    private PihDailyMeetingReportRepo pihDailyMeetingReportRepo;


    @Override
    public List<ResultVo> getDailyReportData(Date date) {

        List<DailyReportDataVo> data = this.pihDailyReportMapper.getDailyReportData(date);

        Map<String , List<DailyReportDataVo>> map = data.stream()
                .collect(Collectors.groupingBy(DailyReportDataVo::getLine));

        List<ResultVo> result = new ArrayList<>();

        for (Map.Entry<String , List<DailyReportDataVo>> entry: map.entrySet()) {
            ResultVo obj = new ResultVo();
            obj.setLine(entry.getKey());
            obj.setDataSource(entry.getValue());
            result.add(obj);
        }

        return result;
    }

    @Override
    public PihDailyMeetingReport saveDailyReportData(PihDailyMeetingReport data) {
        return this.pihDailyMeetingReportRepo.save(data);
    }
}
