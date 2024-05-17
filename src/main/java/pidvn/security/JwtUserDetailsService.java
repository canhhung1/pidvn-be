package pidvn.security;

import org.apache.commons.compress.utils.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidvn.auth.models.AuthVo;
import pidvn.auth.services.AuthService;
import pidvn.entities.one.Users;
import pidvn.mappers.one.auth.AuthMapper;
import pidvn.repositories.one.AccountRepo;
import pidvn.repositories.one.UsersRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * Lấy thông tin user (user info, roles, permissions)
         */

        List<AuthVo> authVoList = this.authMapper.getRoleAndPermissionByUsername(username);

        if (authVoList.size() == 0) {
            throw new UsernameNotFoundException("User chưa được đăng ky!");
        }

        AuthVo auth = authVoList.get(0);

        if (auth.getStatus() == 0) {
            throw new UsernameNotFoundException("Tài khoản: " + username + " đã bị khóa !");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Set<String> permissions = Sets.newHashSet(auth.getPermissions().split(","));

        for (String permission: permissions) {
            if (permission.equals("")) {
                continue;
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        if (auth == null) {
            logger.debug("LOGGER ===>: User chưa được đăng ký");
            throw new UsernameNotFoundException("Nhân viên: " + username + " chưa được đăng ký !");
        }

        return new User(auth.getUsername(), auth.getPassword(), grantedAuthorities);
    }
}
