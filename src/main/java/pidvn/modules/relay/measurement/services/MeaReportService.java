package pidvn.modules.relay.measurement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.relay.measurement.MeasureReportMapper;
import pidvn.modules.relay.measurement.models.report.MeaReportSearchVo;
import pidvn.modules.relay.measurement.models.report.MeaReportVo;

import java.util.List;

@Service
public class MeaReportService implements IMeaReportService {

    @Autowired
    private MeasureReportMapper measureReportMapper;

    /**
     * Thống kê số lượng đo theo Line và Lý do trong ngày
     * @param searchVo
     * @return
     */
    @Override
    public List<MeaReportVo> getMeasurementAmountByLine(MeaReportSearchVo searchVo) {
        return this.measureReportMapper.getMeasurementAmountByLine(searchVo);
    }
}
