package pidvn.mappers.one.commons;

import org.apache.ibatis.annotations.Mapper;
import pidvn.modules.commons.models.UsersSearchVo;
import pidvn.modules.commons.models.UsersVo;

import java.util.List;

@Mapper
public interface CommonsMapper {
    List<UsersVo> getUsers(UsersSearchVo searchVo);
}
