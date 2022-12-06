package pidvn.modules.relay.material_control.services;

import pidvn.entities.one.*;
import pidvn.entities.one.Process;
import pidvn.modules.relay.material_control.models.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IReMatCtrlSvc {
    List<Line> getLines(Integer productId);
    List<Process> getProcesses(Integer productId);
    List<PartVo> getPartsByModel(String model);
    List<SlipVo> getSlips();
    List<SlipVo> getSlipsRelayReturnWarehouse();
    Map getMaterialsBySlipNo(String slipNo);
    Map scanMaterial(MaterialVo materialVo);

    Map saveMaterials(List<MaterialVo> materialVos);
    MaterialControls mappingQaCard(MaterialControls materialControl) throws ParseException;
    List<MaterialControls> getChildQaCards(String parentQaCard);
    Integer deleteChildQaCard(Integer id);

    List<MaterialVo> getMaterials(MaterialSearchVo searchVo);
    List<MaterialVo> materialTraceability(MaterialSearchVo searchVo);
    Map changeQaCard(String oldQaCard, String newQaCard) throws ParseException;
    Integer deleteMaterial(MaterialVo materialVo);
    MaterialControls editMaterialControl(MaterialVo materialVo);

    PurWhRecords savePurWhRecords(PurWhRecords purWhRecords);
    String generateSlipNo(String recordType, String username) throws ParseException;
    List<QaCardVo> getQaCard();

    Map getSlipRelayReturnWarehouseDetail(String slipNo);
    ByteArrayInputStream exportMaterialData(MaterialSearchVo searchVo) throws IOException;

    MaterialControls editQtyImportedToLine(MaterialVo materialVo);

    PurWhRecords deletePurWhRecordById(Integer id);

}
