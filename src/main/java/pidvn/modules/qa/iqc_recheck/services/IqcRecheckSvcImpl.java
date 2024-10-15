package pidvn.modules.qa.iqc_recheck.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IqcDataMaster;
import pidvn.mappers.one.qa.iqc_recheck.IqcRecheckMapper;
import pidvn.modules.qa.iqc_recheck.models.LotDto;
import pidvn.repositories.one.IqcDataMasterRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IqcRecheckSvcImpl implements IqcRecheckSvc {

    @Autowired
    private IqcRecheckMapper iqcRecheckMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IqcDataMasterRepo iqcDataMasterRepo;


    /**
     * Lấy danh sách các lots trong request
     *
     * @param requestNo
     * @return
     */
    @Override
    public List<LotDto> getLotsInRequest(String requestNo, String goodsType) {
            return this.iqcRecheckMapper.getLotsInRequest(requestNo,goodsType);
    }

    @Override
    public Map<String, Object> saveIqcResult(List<LotDto> lots, String goodsType) {

        Map<String, Object> result = new HashMap<>();

        if (goodsType.equals("OUTSIDE")) {
            List<IqcDataMaster> data = lots.stream().map(item -> modelMapper.map(item, IqcDataMaster.class)).collect(Collectors.toList());
            List<IqcDataMaster> savedData = iqcDataMasterRepo.saveAll(data);
            result.put("data", savedData);
        }

        return result;
    }


}
