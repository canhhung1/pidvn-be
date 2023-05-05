package pidvn.modules.warehouse.check_date_iqc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.warehouse.check_date_iqc.CheckDateIqcMapper;
import pidvn.modules.warehouse.check_date_iqc.models.CheckDateIqcVo;

import java.util.List;

@Service
public class CheckDateIqcService implements ICheckDateIqcService{
    @Autowired
    private CheckDateIqcMapper checkDateIqcMapper;
    @Override
    public List<CheckDateIqcVo> getCheckLotNoIqc(CheckDateIqcVo searchVo) {
        List<CheckDateIqcVo> checkDateIqc = this.checkDateIqcMapper.getCheckLotNoIqc(searchVo);
        return checkDateIqc;
    }

}
