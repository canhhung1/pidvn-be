package pidvn.modules.relay.vr_enc_process_recording.services;

import pidvn.entities.one.Line;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.Shifts;
import pidvn.modules.relay.vr_enc_process_recording.models.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IVrEncPRService {
    List<Line> getLines(Integer productId);
    List<ProcessVo> getProcesses(String productTypeName);
    List<ProcessVo> getProcessesVer2(String line);
    Map scanLabel(ScannerVo scannerVo) throws Exception;
    List<MaterialControls> insertMaterials(List<MaterialVo> materialVos);
    MaterialControls updateMaterial(MaterialVo materialVo) throws Exception;
    List<MaterialVo> getMaterials(SearchVo searchVo);
    List<PartVo> getPartsByModel(String model);
    List<Shifts> getShifts();
    List<QaCardVo> getQaCards(SearchVo searchVo);
    ByteArrayInputStream downloadQaCard(SearchVo searchVo) throws IOException;

    List<MaterialVo> traceability(SearchVo searchVo);

    List<ModelVo> getModels();
    Map createQaCard(QaCardVo qaCardVo);

}
