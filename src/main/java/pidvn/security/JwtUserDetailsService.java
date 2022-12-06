package pidvn.security;

import org.apache.commons.compress.utils.Sets;
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

        Users user = this.usersRepo.findByUsername(username);

        List<AuthVo> authVoList = this.authMapper.getRoleAndPermissionByUsername(username);

        AuthVo result = authVoList.get(0);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Set<String> permissions = Sets.newHashSet(result.getPermissions().split(","));

        for (String permission: permissions) {
            if (permission.equals("")) {
                continue;
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        if (user == null) {
            throw new UsernameNotFoundException("Nhân viên: " + username + " chưa được đăng ký !");
        }

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
