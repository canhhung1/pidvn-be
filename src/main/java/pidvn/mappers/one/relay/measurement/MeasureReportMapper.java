package pidvn.mappers.one.relay.measurement;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.relay.measurement.models.report.MeaReportSearchVo;
import pidvn.modules.relay.measurement.models.report.MeaReportVo;

import java.util.List;

@Mapper
public interface MeasureReportMapper {
    /**
     * Thống kê số lượng đo theo Line trong ngày
     * @param searchVo
     * @return
     */
    List<MeaReportVo> getMeasurementAmountByLine(MeaReportSearchVo searchVo);
}
