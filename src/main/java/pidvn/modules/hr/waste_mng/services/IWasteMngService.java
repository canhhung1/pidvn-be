package pidvn.modules.hr.waste_mng.services;

import net.sf.jasperreports.engine.JRException;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.hr.waste_mng.models.WasteDataVo;
import pidvn.modules.hr.waste_mng.models.WasteSearchVo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IWasteMngService {
    List<Users> getUsers();
    WasteMasterData createWasteMasterData(WasteMasterData masterData);
    Map createWasteDetailData(WasteDataVo dataVo);
    List<WasteDataVo> getWasteMasterData(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailData(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailDataSummary(WasteSearchVo searchVo);
    List<WasteDataVo> getWasteDetailDataSummaryAll(WasteSearchVo searchVo);

    List<WasteGroup> getWasteGroup();
    List<WasteType> getWasteType(Integer wasteGroup);
    List<WasteHandleCompany> getHandleCompany();
    WasteImage uploadImage(MultipartFile file) throws IOException;
    Integer removeImage(Integer id) throws Exception;
    byte[] exportMasterData(Integer masterId) throws JRException;
    byte[] exportChungTu(Integer masterId) throws JRException;

    List<WasteDetailData> setupUnitPrice(Integer wasteMaster);

    void removeWasteMasterData(Integer id);
    void removeWasteDetailData(Integer id);
    ByteArrayInputStream exportExcel(WasteSearchVo searchVo) throws Exception;
}
