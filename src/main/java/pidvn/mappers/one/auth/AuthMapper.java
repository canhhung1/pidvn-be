package pidvn.mappers.one.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pidvn.auth.models.AccountVo;
import pidvn.auth.models.AuthVo;
import pidvn.auth.models.RolePermissionVo;

import java.util.List;

@Mapper
public interface AuthMapper {
    List<AuthVo> getRoleAndPermissionByUsername(@Param("username") String username);
    List<RolePermissionVo> getRoleAndPermissionByUsernameV2(@Param("username") String username);
    List<AccountVo> getUserInfo(@Param("username") String username);
}
