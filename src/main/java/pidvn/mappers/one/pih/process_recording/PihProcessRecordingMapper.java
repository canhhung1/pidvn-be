package pidvn.mappers.one.pih.process_recording;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.pih.process_recording.models.DefectRecordVo;
import pidvn.modules.pih.process_recording.models.LotVo;
import pidvn.modules.pih.process_recording.models.MaterialSearchVo;
import pidvn.modules.pih.process_recording.models.MaterialVo;

import java.util.List;

@Mapper
public interface PihProcessRecordingMapper {
    List<MaterialVo> getMaterial(MaterialSearchVo searchVo);

    List<LotVo> getLots(LotVo searchVo);

    /**
     *
     * @param label tem thành phẩm
     * @param bobbinAmount tổng số các vị trí dây đồng đang chạy
     * @param coilCode mã cuộn dây đồng (W-G180-84, W-G180-90, ...)
     * @return
     */
    Float calculateQtyChangeCoil(String label, Integer fromBox, Integer toBox, Integer bobbinAmount, String coilCode);



    List<DefectRecordVo> getDefectRecords(String line, String lotNo, Integer limit);

    List<LotVo> calculateQtyChangeLabel(int bobbinAmount, String plotno, List<Integer> ids);

}
