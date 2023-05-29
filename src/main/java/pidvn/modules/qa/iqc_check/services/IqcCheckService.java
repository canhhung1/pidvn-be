package pidvn.modules.qa.iqc_check.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pidvn.entities.one.*;
import pidvn.mappers.one.qa.iqc_check.IqcCheckMapper;
import pidvn.modules.qa.iqc_check.models.*;
import pidvn.repositories.one.*;

import java.util.List;
import java.util.Map;

@Service
public class IqcCheckService implements IIqcCheckService {

    @Autowired
    private IqcCheckMapper iqcCheckMapper;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    @Autowired
    private IqcDataRepo iqcDataRepo;

    @Autowired
    private IqcDataMasterRepo iqcDataMasterRepo;

    @Autowired
    private IqcDataDetailRepo iqcDataDetailRepo;

    @Autowired
    private AuditConfigFdcsRepo auditConfigFdcsRepo;

    @Override
    public List<IqcRequestVo> getIqcRequests(IqcRequestSearchVo searchVo) {
        String isConfig = this.auditConfigFdcsRepo.findByConfigName("iqc_check_audit").get(0).getConfigValue();
        searchVo.setIsAudit(isConfig);
        return this.iqcCheckMapper.getIqcRequests(searchVo);
    }

    @Override
    public List<IqcDataVo> getIqcDataMaster(IqcDataSearchVo searchVo) {
        /**
         * Config khi audit
         * Nếu điều kiện = "TRUE"
         * Lấy thông tin config từ bảng audit_config_fdcs
         */
        String isConfig = this.auditConfigFdcsRepo.findByConfigName("iqc_check_audit").get(0).getConfigValue();
        searchVo.setIsAudit(isConfig);
        return this.iqcCheckMapper.getIqcDataMaster(searchVo);
    }

    @Override
    public List<IqcDataVo> getIqcDataDetail(IqcDataSearchVo searchVo) {
        /**
         * Config khi audit
         * Nếu điều kiện = "TRUE"
         * Lấy thông tin config từ bảng audit_config_fdcs
         */
         String isConfig = this.auditConfigFdcsRepo.findByConfigName("iqc_check_audit").get(0).getConfigValue();
         searchVo.setIsAudit(isConfig);
        return this.iqcCheckMapper.getIqcDataDetail(searchVo);
    }

    @Override
    public IqcDataMaster saveIqcDataMaster(IqcDataVo iqcDataVo) {

        //List<IqcDataMaster> data = this.iqcDataMasterRepo.findByRequestNoAndLotGroup(iqcDataVo.getRequestNo(), iqcDataVo.getLotGroup());
        List<IqcDataMaster> data = this.iqcDataMasterRepo.findByRequestNoAndLotGroupAndModel(iqcDataVo.getRequestNo(), iqcDataVo.getLotGroup(), iqcDataVo.getModel());

        IqcDataMaster master;

        if (data.size() != 0) {
            master = data.get(0);
        } else {
            master = new IqcDataMaster();
        }

        master.setInvoice(iqcDataVo.getInvoice());
        master.setRequestNo(iqcDataVo.getRequestNo());
        master.setLotGroup(iqcDataVo.getLotGroup());
        master.setModel(iqcDataVo.getModel());
        master.setResult1(iqcDataVo.getResult1());
        master.setResult2(iqcDataVo.getResult2());
        master.setResult3(iqcDataVo.getResult3());
        master.setRemark(iqcDataVo.getRemark());
        master.setCreatedBy(iqcDataVo.getCreatedBy());

        return this.iqcDataMasterRepo.save(master);
    }

    @Override
    public IqcDataDetail saveIqcDataDetail(IqcDataVo iqcDataVo) {

        List<IqcDataDetail> data = this.iqcDataDetailRepo.findByRequestNoAndLotNo(iqcDataVo.getRequestNo(), iqcDataVo.getLotNo());

        if (data.size() != 0) {
            IqcDataDetail detail = data.get(0);
            detail.setResult1(iqcDataVo.getResult1());
            detail.setResult2(iqcDataVo.getResult2());
            detail.setResult3(iqcDataVo.getResult3());
            detail.setRemark(iqcDataVo.getRemark());
            detail.setCreatedBy(iqcDataVo.getCreatedBy());
            return this.iqcDataDetailRepo.save(detail);
        }

        IqcDataDetail detail = new IqcDataDetail();
        detail.setInvoice(iqcDataVo.getInvoice());
        detail.setRequestNo(iqcDataVo.getRequestNo());
        detail.setLotGroup(iqcDataVo.getLotGroup());
        detail.setLotNo(iqcDataVo.getLotNo());
        detail.setResult1(iqcDataVo.getResult1());
        detail.setResult2(iqcDataVo.getResult2());
        detail.setResult3(iqcDataVo.getResult3());
        detail.setRemark(iqcDataVo.getRemark());
        detail.setCreatedBy(iqcDataVo.getCreatedBy());

        return this.iqcDataDetailRepo.save(detail);
    }

    @Override
    public IqcRequest updateIqcRequest(String requestNo, Integer status) throws Exception {

        IqcRequest request = this.iqcRequestRepo.findByRequestNo(requestNo);
        if (request == null) {
            throw new Exception("Mã request không tồn tại !");
        }

        request.setStatus(status);

        return this.iqcRequestRepo.save(request);
    }

    @Override
    public void deleteIqcDataDetail(Integer id) {
        this.iqcDataDetailRepo.deleteById(id);
    }

    @Override
    public AuditConfigFdcs changeConfigAudit(String configValue) {
        String configName = "iqc_check_audit";
        AuditConfigFdcs config = this.auditConfigFdcsRepo.findByConfigName(configName).get(0);
        config.setConfigValue(configValue);
        return this.auditConfigFdcsRepo.save(config);
    }

    @Override
    public AuditConfigFdcs getConfigAudit(String configName) {
        AuditConfigFdcs configFdcs = this.auditConfigFdcsRepo.findByConfigName(configName).get(0);
        return configFdcs;
    }

}
