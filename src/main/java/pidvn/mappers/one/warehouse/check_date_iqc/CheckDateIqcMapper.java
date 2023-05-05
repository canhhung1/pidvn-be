package pidvn.mappers.one.warehouse.check_date_iqc;


import pidvn.modules.warehouse.check_date_iqc.models.CheckDateIqcVo;

import java.util.List;

public interface CheckDateIqcMapper {
    List<CheckDateIqcVo> getCheckLotNoIqc(CheckDateIqcVo searchVo);
}
