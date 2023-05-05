package pidvn.modules.warehouse.check_date_iqc.services;

import pidvn.modules.warehouse.check_date_iqc.models.CheckDateIqcVo;

import java.util.List;

public interface ICheckDateIqcService {
    List<CheckDateIqcVo> getCheckLotNoIqc(CheckDateIqcVo searchVo);
}
