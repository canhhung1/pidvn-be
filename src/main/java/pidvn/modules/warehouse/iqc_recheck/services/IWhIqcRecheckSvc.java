package pidvn.modules.warehouse.iqc_recheck.services;

import pidvn.entities.one.Lots;
import pidvn.modules.warehouse.iqc_recheck.models.LabelDto;

import java.util.List;

public interface IWhIqcRecheckSvc {

    List<Lots> scanLabel(LabelDto labelDto);
}
