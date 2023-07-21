package pidvn.modules.qa.iqc_check.services;

import org.springframework.web.bind.annotation.RequestParam;
import pidvn.entities.one.*;
import pidvn.modules.qa.iqc_check.models.*;

import java.util.List;
import java.util.Map;

public interface IIqcCheckService {
    List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo);
    List<IqcDataVo> getIqcDataMaster(IqcDataSearchVo searchVo);
    List<IqcDataVo> getIqcDataDetail(IqcDataSearchVo searchVo);

    IqcDataMaster saveIqcDataMaster(IqcDataVo masterVo);
    IqcDataDetail saveIqcDataDetail(IqcDataVo detailVo);

    IqcRequest updateIqcRequest(String requestNo, Integer status) throws Exception;

    void deleteIqcDataDetail(Integer id);

    AuditConfigFdcs changeConfigAudit(String configValue);
    AuditConfigFdcs getConfigAudit(String configName);

    List<IqcDataVo> getIqcDataSortingMaster(String requestNo);

    List<IqcDataVo> getIqcDataSortingDetail(String requestNo, String lotGroup);

    IqcDataSortingDetail saveIqcDataSortingDetail(IqcDataVo iqcDataVo);

    IqcDataSortingMaster saveIqcDataSortingMaster(IqcDataVo iqcDataVo);

    void deleteIqcDataSortingDetail(Integer id);

    List<IqcDataMaster> getMucDoKiemSoat(String model);

    List<IqcLevelOfControl> getIqcLevelOfControls();

}
