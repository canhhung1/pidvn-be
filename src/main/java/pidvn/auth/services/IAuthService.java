package pidvn.auth.services;

import org.apache.ibatis.annotations.Param;
import pidvn.auth.models.AccountVo;
import pidvn.auth.models.PasswordVo;
import pidvn.entities.one.Account;
import pidvn.entities.one.Users;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface IAuthService {
    Account createAccount(AccountVo accountVo) throws Exception;
    Users findUserByUsername(String username)throws Exception;
    Map getRoleAndPermissionByUsername(String username) throws Exception;
    List<AccountVo> getUserInfo(@Param("username") String username);
    Map changePassword(String username, PasswordVo passwordVo) throws Exception;
    Map forgotPassword(AccountVo accountVo) throws MessagingException, UnsupportedEncodingException;


    Map generateJWT(String username) throws Exception;
    Map getRoleAndPermissionByUsernameV2(String username) throws Exception;
}
