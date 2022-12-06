package pidvn.modules.relay.measurement.services;

import pidvn.modules.relay.measurement.models.report.MeaReportSearchVo;
import pidvn.modules.relay.measurement.models.report.MeaReportVo;

import java.util.List;

public interface IMeaReportService {
    List<MeaReportVo> getMeasurementAmountByLine(MeaReportSearchVo searchVo);
}
