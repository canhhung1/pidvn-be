package pidvn.mappers.one.pih.process_recording;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.pih.process_recording.models.MaterialSearchVo;
import pidvn.modules.pih.process_recording.models.MaterialVo;

import java.util.List;

@Mapper
public interface PihProcessRecordingMapper {
    List<MaterialVo> getMaterial(MaterialSearchVo searchVo);
}
