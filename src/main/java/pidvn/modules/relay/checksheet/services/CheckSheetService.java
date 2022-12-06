package pidvn.modules.relay.checksheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.*;
import pidvn.mappers.one.relay.checksheet.CheckSheetMapper;
import pidvn.modules.relay.checksheet.models.*;
import pidvn.repositories.one.*;

import java.util.List;

@Service
public class CheckSheetService implements ICheckSheetService{

    @Autowired
    private CheckSheetMapper checkSheetMapper;

    @Autowired
    private CsoItemRepo csoItemRepo;

    @Autowired
    private CsoMasterRepo csoMasterRepo;

    @Autowired
    private CsoDetailRepo csoDetailRepo;

    @Autowired
    private CsoModelRepo csoModelRepo;

    @Autowired
    private CsoProcessRepo csoProcessRepo;

    @Override
    public List<MasterVo> getCheckSheetMaster(MasterSearchVo searchVo) {
        return this.checkSheetMapper.getCheckSheetMaster(searchVo);
    }

    @Override
    public List<ProcessVo> getProcesses(Integer master, String line, String model) {
        return this.checkSheetMapper.getProcesses(master, line, model);
    }

    @Override
    public List<ItemVo> getItems(Integer master, Integer process) {
        return this.checkSheetMapper.getItems(master, process);
    }

    @Override
    public CsoItem addItem(ItemVo itemVo) {

        CsoItem csoItem = new CsoItem();

        csoItem.setName(itemVo.getName());
        csoItem.setUnit(itemVo.getUnit());
        csoItem.setType(itemVo.getType());
        csoItem.setMinValue(itemVo.getMinValue());
        csoItem.setMaxValue(itemVo.getMaxValue());
        csoItem.setProcess(itemVo.getProcess());

        return this.csoItemRepo.save(csoItem);
    }

    @Override
    public List<DetailVo> getDetails(String line, Integer master) {
        return this.checkSheetMapper.getDetails(line, master);
    }

    @Override
    public CsoMaster approveCheckSheet(MasterVo masterVo) {
        CsoMaster master = this.csoMasterRepo.findByQaCard(masterVo.getQaCard());
        master.setApproveBy(masterVo.getApproveBy());
        master.setRemark(masterVo.getRemark());
        master.setApproveStatus(masterVo.getApproveStatus());
        return this.csoMasterRepo.save(master);
    }

    @Override
    public CsoDetail editDetail(DetailVo detailVo) {

        if (detailVo.getDetail() == null) {

            CsoDetail detail = new CsoDetail();
            detail.setMaster(detailVo.getMaster());
            detail.setProcess(detailVo.getProcess());
            detail.setItem(detailVo.getId());
            detail.setActualValue(detailVo.getActualValue());
            detail.setEvaluate(detailVo.getEvaluate());
            detail.setNote(detailVo.getNote());
            detail.setEmployee(detailVo.getEmployee());

            return this.csoDetailRepo.save(detail);
        }

        CsoDetail detail = this.csoDetailRepo.findById(detailVo.getDetail()).get();

        detail.setActualValue(detailVo.getActualValue());
        detail.setNote(detailVo.getNote());
        detail.setEvaluate(detailVo.getEvaluate());
        detail.setEmployee(detailVo.getEmployee());

        return this.csoDetailRepo.save(detail);
    }

    @Override
    public List<CsoModel> getModels() {
        return this.csoModelRepo.findAllByOrderByNameAsc();
    }

    @Override
    public CsoProcess updateProcess(ProcessVo processVo) {
        CsoProcess process = this.csoProcessRepo.getById(processVo.getId());
        process.setModels(processVo.getModels());
        return this.csoProcessRepo.save(process);
    }
}
