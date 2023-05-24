package pidvn.modules.qa.qa_equipment_mng.services;

import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.QaDeviceCalibrationLabel;
import pidvn.modules.qa.qa_equipment_mng.models.LabelVo;
import pidvn.modules.qa.qa_equipment_mng.models.QaDocDeviceVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IQaEquipmentMngSvc {
    List<LabelVo> createLabel( MultipartFile file) throws IOException;

    Map uploadDocument(MultipartFile file, String controlNo, Integer deviceId, Integer fileType, String createdBy) throws IOException;

    List<QaDocDeviceVo> getQaDocDevices(Integer deviceId);

    Map getDeviceInfo(String controlNo) throws Exception;

    List<QaDeviceCalibrationLabel> printLabel(List<LabelVo> labelVos, Integer userId);

}
