package pidvn.modules.qa.iqc_check.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pidvn.entities.one.*;
import pidvn.mappers.one.qa.iqc_check.IqcCheckMapper;
import pidvn.modules.qa.iqc_check.models.*;
import pidvn.repositories.one.*;

import java.util.*;

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
    private IqcDataSortingMasterRepo iqcDataSortingMasterRepo;

    @Autowired
    private IqcDataSortingDetailRepo iqcDataSortingDetailRepo;

    @Autowired
    private IqcRequestSortingDetailRepo iqcRequestSortingDetailRepo;

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
        master.setLevelOfControlNgoaiQuan(iqcDataVo.getLevelOfControlNgoaiQuan());
        master.setLevelOfControlKichThuoc(iqcDataVo.getLevelOfControlKichThuoc());

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

    @Override
    public List<IqcDataVo> getIqcDataSortingMaster(String requestNo) {
        return this.iqcCheckMapper.getIqcDataSortingMaster(requestNo);
    }

    @Override
    public List<IqcDataVo> getIqcDataSortingDetail(String requestNo, String lotGroup) {
        return this.iqcCheckMapper.getIqcDataSortingDetail(requestNo, lotGroup);
    }

    @Override
    public IqcDataSortingDetail saveIqcDataSortingDetail(IqcDataVo iqcDataVo) {

        if (iqcDataVo.getId() != null) {
            IqcDataSortingDetail detail = this.iqcDataSortingDetailRepo.findById(iqcDataVo.getId()).get();
            detail.setResult1(iqcDataVo.getResult1());
            detail.setResult2(iqcDataVo.getResult2());
            detail.setResult3(iqcDataVo.getResult3());
            detail.setRemark(iqcDataVo.getRemark());
            detail.setCreatedBy(iqcDataVo.getCreatedBy());
            return this.iqcDataSortingDetailRepo.save(detail);
        }

        IqcDataSortingDetail detail = new IqcDataSortingDetail();
        detail.setRequestNo(iqcDataVo.getRequestNo());
        detail.setLotGroup(iqcDataVo.getLotGroup());
        detail.setLotNo(iqcDataVo.getLotNo());
        detail.setResult1(iqcDataVo.getResult1());
        detail.setResult2(iqcDataVo.getResult2());
        detail.setResult3(iqcDataVo.getResult3());
        detail.setRemark(iqcDataVo.getRemark());
        detail.setCreatedBy(iqcDataVo.getCreatedBy());
        return this.iqcDataSortingDetailRepo.save(detail);
    }

    @Override
    public IqcDataSortingMaster saveIqcDataSortingMaster(IqcDataVo iqcDataVo) {

        List<IqcDataSortingMaster> data = this.iqcDataSortingMasterRepo.findByRequestNoAndLotGroup(iqcDataVo.getRequestNo(), iqcDataVo.getLotGroup());

        IqcDataSortingMaster master;
        if (data.size() != 0) {
            master = data.get(0);
        } else {
            master = new IqcDataSortingMaster();
        }

        master.setRequestNo(iqcDataVo.getRequestNo());
        master.setLotGroup(iqcDataVo.getLotGroup());
        master.setResult1(iqcDataVo.getResult1());
        master.setResult2(iqcDataVo.getResult2());
        master.setResult3(iqcDataVo.getResult3());
        master.setRemark(iqcDataVo.getRemark());
        master.setCreatedBy(iqcDataVo.getCreatedBy());

        IqcDataSortingMaster result = this.iqcDataSortingMasterRepo.save(master);


        /**
         * Lưu giá trị vào bảng iqc_request_sorting_detail;
         */
        // B1: Tìm các lot cần lưu

        List<IqcRequestSortingDetail> details = this.iqcCheckMapper.getIqcRequestSortingDetails(iqcDataVo.getRequestNo(), iqcDataVo.getLotGroup());


        List<IqcRequestSortingDetail> dataSave = new ArrayList<>();

        for (IqcRequestSortingDetail item : details) {

            String result1 = iqcDataVo.getResult1();
            String result2 = iqcDataVo.getResult2();
            String result3 = iqcDataVo.getResult3();
            String remark = iqcDataVo.getRemark();
            Date createdAt = iqcDataVo.getCreatedAt();

            item.setResult1(result1);
            item.setResult2(result2);
            item.setResult3(result3);
            item.setRemark(remark);
            item.setCreatedAt(createdAt);

            dataSave.add(item);
        }

        this.iqcRequestSortingDetailRepo.saveAll(dataSave);

        return result;
    }

    @Override
    public void deleteIqcDataSortingDetail(Integer id) {
        this.iqcDataSortingDetailRepo.deleteById(id);
    }

    @Override
    public List<IqcDataMaster> getMucDoKiemSoat(String model) {
        return this.iqcCheckMapper.getMucDoKiemSoat(model);
    }

}
