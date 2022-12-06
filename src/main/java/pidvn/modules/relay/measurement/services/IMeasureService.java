package pidvn.modules.relay.measurement.services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.modules.relay.measurement.models.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IMeasureService {
    List<MeasureItemVo> getItems();
    Optional<MeaItem> getItemById(Integer id);
    Optional<MeaItem> getItems(Integer id);
    List<MeaStandard> getStandard(Integer item, String modelType);
    List<MeasureDataVo> saveMeasureData(List<MeasureDataVo> dataVoList);
    List<MeaItemImage> getItemImages(Integer item);
    List<MeaItemImage> getItemImages(Integer item, String modelType);
    List<MeasureMasterDataVo> getMasterData(MeasureSearchVo searchVo);
    List<MeasureDetailDataVo> getDetailData(MeasureSearchVo searchVo);
    MeaMasterData approveData(Integer masterId, String username);
    List<MeaMasterData> quickApprove(List<MeaMasterData> masterDataList, String approver);
    List<MeaDetailData> updateDetailData(List<MeaDetailData> detailList);
    List<MeaChildItem> getChildItems(Integer item, String modelType);
    MeaItemImage uploadImage(MultipartFile file, Integer item, String modelType) throws IOException;
    void removeImage(Integer id);
}
