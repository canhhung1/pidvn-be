package pidvn.modules.qa.iqc_recheck.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.qa.iqc_recheck.IqcRecheckMapper;
import pidvn.modules.qa.iqc_recheck.models.LotDto;

import java.util.List;

@Service
public class IqcRecheckSvcImpl implements IqcRecheckSvc {

    @Autowired
    private IqcRecheckMapper iqcRecheckMapper;


    /**
     * Lấy danh sách các lots trong request
     *
     * @param requestNo
     * @return
     */
    @Override
    public List<LotDto> getLotsInRequest(String requestNo) {
        return null;
    }



}
