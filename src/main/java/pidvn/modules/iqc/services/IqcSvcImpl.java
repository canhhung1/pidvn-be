package pidvn.modules.iqc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.mappers.one.iqc.IqcMapper;
import pidvn.modules.iqc.models.IqcRequestDto;
import pidvn.modules.iqc.models.PurWhRecordDto;
import pidvn.modules.iqc.models.SearchDto;

import java.util.List;

@Service
public class IqcSvcImpl implements IqcSvc {

    @Autowired
    private IqcMapper iqcMapper;

    @Override
    public List<IqcRequestDto> getIqcRequests(SearchDto searchDto) {
        return this.iqcMapper.getIqcRequests(searchDto);
    }

    @Override
    public List<PurWhRecordDto> getSlipNo() {
        return this.iqcMapper.getSlipNo();
    }
}
