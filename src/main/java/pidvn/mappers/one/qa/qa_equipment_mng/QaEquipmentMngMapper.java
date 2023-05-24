package pidvn.mappers.one.qa.qa_equipment_mng;

import org.apache.ibatis.annotations.Mapper;
import pidvn.entities.one.QaDeviceCalibrationLabel;
import pidvn.modules.qa.qa_equipment_mng.models.QaDeviceVo;
import pidvn.modules.qa.qa_equipment_mng.models.QaDocDeviceVo;

import java.util.List;

@Mapper
public interface QaEquipmentMngMapper {
    List<QaDocDeviceVo> getQaDocDevices(Integer deviceId);

    /**
     * Lấy các số lần in tem
     * @return
     */
    List<QaDeviceCalibrationLabel> getPrintCodeInDay();

    List<QaDeviceVo> getDeviceInfo(String controlNo);
}
