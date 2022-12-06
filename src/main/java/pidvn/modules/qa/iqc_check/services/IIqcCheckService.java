package pidvn.modules.qa.iqc_check.services;

import org.springframework.web.bind.annotation.RequestParam;
import pidvn.entities.one.IqcData;
import pidvn.entities.one.IqcDataDetail;
import pidvn.entities.one.IqcDataMaster;
import pidvn.entities.one.IqcRequest;
import pidvn.modules.qa.iqc_check.models.*;

import java.util.List;

public interface IIqcCheckService {
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
    List<IqcDataVo> getIqcDataMaster(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataDetail(IqcDataSearchVo searchVo);

    IqcDataMaster saveIqcDataMaster(IqcDataVo masterVo);
    IqcDataDetail saveIqcDataDetail(IqcDataVo detailVo);

    IqcRequest updateIqcRequest(String requestNo, Integer status) throws Exception;

    void deleteIqcDataDetail(Integer id);

}
