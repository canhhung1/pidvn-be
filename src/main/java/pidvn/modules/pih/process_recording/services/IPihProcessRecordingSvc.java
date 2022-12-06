package pidvn.modules.pih.process_recording.services;

import pidvn.entities.one.Line;
import pidvn.entities.one.MaterialControls;
import pidvn.entities.one.PsMaster;
import pidvn.modules.pih.process_recording.models.MaterialSearchVo;
import pidvn.modules.pih.process_recording.models.MaterialVo;
import pidvn.modules.pih.process_recording.models.ScannerVo;

import java.util.List;
import java.util.Map;

public interface IPihProcessRecordingSvc {

    List<PsMaster> getPsMasters(String pnpa);
    Map insertCoil(ScannerVo scannerVo) throws Exception;
    Map insertCoilManual(ScannerVo scannerVo) throws Exception;
    List<MaterialVo> getMaterials(MaterialSearchVo searchVo);
    Map changeLabel(ScannerVo scannerVo) throws Exception;
    List<MaterialControls> changeModel(ScannerVo scannerVo);
    List<Line> getLines(Integer productId);
    Map scanCoil(MaterialSearchVo searchVo);

}
