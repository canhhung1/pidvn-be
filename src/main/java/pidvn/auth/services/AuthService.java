package pidvn.auth.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.auth.models.AccountVo;
import pidvn.auth.models.AuthVo;
import pidvn.auth.models.PasswordVo;
import pidvn.auth.utils.EmailUtil;
import pidvn.auth.utils.RandomUtil;
import pidvn.entities.one.Account;
import pidvn.entities.one.Users;
import pidvn.mappers.one.auth.AuthMapper;
import pidvn.repositories.one.AccountRepo;
import pidvn.repositories.one.UsersRepo;
import pidvn.security.JwtTokenUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class AuthService implements IAuthService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Account createAccount(AccountVo accountVo) throws Exception {

        if (this.usersRepo.findByUsername(accountVo.getUsername()) == null) {
            throw new Exception("Chưa có dữ liệu nhân viên trong hệ thống !");
        }
        if (this.accountRepo.findByUsername(accountVo.getUsername()) != null) {
            throw new Exception("Username: " + accountVo.getUsername() + " đã tồn tại !");
        }
        Account account = new Account();
        account.setUsername(accountVo.getUsername());
        account.setFullName(accountVo.getFullName());
        account.setPassword(this.bcryptEncoder.encode(accountVo.getPassword()));
        account.setEmail(accountVo.getEmail());

        return this.accountRepo.save(account);
    }

    @Override
    public Users findUserByUsername(String username) throws Exception {
        return this.usersRepo.findByUsername(username);
    }

    @Override
    public Map getRoleAndPermissionByUsername(String username) throws Exception {
        List<AuthVo> authVoList = this.authMapper.getRoleAndPermissionByUsername(username);

        if (authVoList.size() < 0) {
            throw new Exception("Có lỗi về Role & Permission");
        }

        AuthVo result = authVoList.get(0);

        Set setRoles = Sets.newHashSet(result.getRoles().split(","));
        Set setPermissions = Sets.newHashSet(result.getPermissions().split(","));

        Map map = new HashMap();
        map.put("roles", setRoles);
        map.put("permissions", setPermissions);

        return map;
    }

    @Override
    public List<AccountVo> getUserInfo(String username) {
        return this.authMapper.getUserInfo(username);
    }

    @Override
    public Map changePassword(String username, PasswordVo passwordVo) throws Exception {

        Users user = this.usersRepo.findByUsername(username);

        boolean isMatchPassword = this.bcryptEncoder.matches(passwordVo.getCurrentPassword(), user.getPassword());

        if (!isMatchPassword) {
            throw new Exception("Your Current Password is wrong!");
        }
        user.setPassword(this.bcryptEncoder.encode(passwordVo.getConfirmPassword()));
        this.usersRepo.save(user);
        Map result = new HashMap();
        result.put("Username", user.getUsername());
        result.put("Status", "Change password success !");
        return result;
    }

    @Override
    @Transactional(transactionManager = "transactionManagerOne")
    public Map forgotPassword(AccountVo accountVo) throws MessagingException, UnsupportedEncodingException {

        Users user = this.usersRepo.findByEmail(accountVo.getEmail());

        Map result = new HashMap();

        // TH1: Email không tồn tại trong hệ thống
        if (user == null) {
            result.put("status","Error");
            result.put("response","Email chưa được đăng ký trong hệ thống !");
            return result;
        }

        // TH2: Email tồn tại trong hệ thống
        // Tạo mật khẩu mới (8 ký tự gồm chữ, số, ký tự đặc biệt)
        String password = RandomUtil.random(3,"character")
                + RandomUtil.random(4,"number")
                + RandomUtil.random(1,"special");

        // Update mật khẩu trong database
        user.setPassword(this.bcryptEncoder.encode(password));
        this.usersRepo.save(user);

        // Gửi mail thông báo
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Cấp lại mật khẩu truy cập hệ thống FDCS <br/>");
        emailContent.append("Mật khẩu được cấp lại cho nhân viên: ").append(user.getName()).append(" (").append(user.getUsername()).append(") là: ");
        emailContent.append("<b style=\"color: orange;font-size:25px \">").append(password).append("</b> <br/>");
        emailContent.append("Bạn nên đổi lại mật khẩu cho dễ nhớ sau khi đăng nhập thành công! <br/> Chào thân ái và quyết thắng!");

        this.sendMail("[THÔNG BÁO] - Thông tin mật khẩu FDCS",user.getEmail(),null, emailContent.toString(), "PIDVN");

        result.put("status","Success");
        result.put("response","Hệ thống đã gửi một email chứa mật khẩu tới email của bạn !");
        return result;
    }

    public void sendMail(String subject, String to, String [] bcc, String body, String personal) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("it.pidvn", personal);
        helper.setSubject(subject);
        helper.setTo(to);

        if (bcc != null) {
            helper.setBcc(bcc);
        }
        helper.setText(body, true);
        this.mailSender.send(message);
        System.out.println("======> SEND EMAIL DONE !");
    }

    @Override
    public Map generateJWT(String username) throws Exception {

        AccountVo user = this.authMapper.getUserInfo(username).get(0);

        Map rolesPermissions = this.getRoleAndPermissionByUsernameV2(username);

        // Add some user's info to token
        Map<String, Object> claims = new HashMap<>();
        claims.put("Username", user.getUsername());
        claims.put("FullName", user.getFullName());
        claims.put("Email", user.getEmail());
        claims.put("Section", user.getSection());
        claims.put("SectionCode", user.getSectionCode());
        claims.put("SubSection", user.getSubsection());
        claims.put("SubSectionCode", user.getSubsectionCode());
        claims.put("Position", user.getPosition());
        claims.put("PositionId", user.getPositionId());
        claims.put("Roles", rolesPermissions.get("roles"));
        claims.put("Permissions", rolesPermissions.get("permissions"));

        String token = Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        Map result = new HashMap();
        result.put("token" , token);
        return result;
    }

    /**
     * Sử dụng riêng cho hệ thống E-Leave
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public Map getRoleAndPermissionByUsernameV2(String username) throws Exception {
        List<AuthVo> authVoList = this.authMapper.getRoleAndPermissionByUsername(username);

        if (authVoList.size() < 0) {
            throw new Exception("Có lỗi về Role & Permission");
        }

        AuthVo result = authVoList.get(0);

        Set setRoles = Sets.newHashSet(result.getRoles().split(","));
        Set setPermissions = Sets.newHashSet(result.getPermissions().split(","));

        Map map = new HashMap();
        map.put("roles", setRoles);
        map.put("permissions", setPermissions);

        return map;
    }

}
