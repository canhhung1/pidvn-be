package pidvn.modules.relay.material_management.services;

import org.springframework.web.bind.annotation.RequestBody;
import pidvn.entities.one.InspectQaCard;
import pidvn.entities.one.Line;
import pidvn.entities.one.Process;
import pidvn.entities.one.PurWhRecords;
import pidvn.entities.one.TmpAdrTnmu010;
import pidvn.modules.relay.material_management.models.PurWhRecordSearchVo;
import pidvn.modules.relay.material_management.models.PurWhRecordVo;
import pidvn.modules.relay.material_management.models.SearchVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IReMaterialMngSvc {
    List<Line> getLines(Integer productId);
    List<Process> getProcesses(Integer productId);
    List<TmpAdrTnmu010> getPartsByModel(String model);
    List<PurWhRecordVo> getPurWhRecords(PurWhRecordSearchVo searchVo);
    Map scanMaterial(PurWhRecordVo purWhRecordVo);
    List<PurWhRecords> savePurWhRecords(List<PurWhRecords> purWhRecords);
    Map getLotsBySlipNo(String slipNo);
    Integer deletePurWhRecord(Integer id);

    Map changeQaCard(String oldQaCard, String newQaCard) throws ParseException;
    InspectQaCard saveInspectQACard(InspectQaCard inspectQaCard);
    List<InspectQaCard> getInspectQaCards(String inspectQaCard);
    Integer deleteInspectQaCard(Integer id);

    List<PurWhRecordVo> materialTraceability(SearchVo searchVo);
}
