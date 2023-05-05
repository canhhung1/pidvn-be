package pidvn.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pidvn.auth.models.AccountVo;
import pidvn.auth.models.JwtResponse;
import pidvn.auth.models.PasswordVo;
import pidvn.auth.services.AuthService;
import pidvn.entities.one.Account;
import pidvn.security.JwtTokenUtil;
import pidvn.security.JwtUserDetailsService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("Auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @GetMapping("home")
    public String home() {
        return "Panasonic Industrial Devices Vietnam";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AccountVo accountDto) throws Exception {

        LOGGER.debug("LOGGER ===>: createAuthenticationToken: ");

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(accountDto.getUsername());

        authenticate(accountDto.getUsername(), accountDto.getPassword());

        final String token = jwtTokenUtil.generateToken(userDetails);

        LOGGER.debug("LOGGER ===> JWT: " + token);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountVo accountDto) throws Exception {
        Account result = this.authService.createAccount(accountDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ChangePassword/{username}")
    public ResponseEntity<?> changePassword(@PathVariable String username, @RequestBody PasswordVo passwordVo ) throws Exception {
        return new ResponseEntity<>(this.authService.changePassword(username, passwordVo), HttpStatus.OK);
    }

    @PostMapping("/ForgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody AccountVo accountVo) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<>(this.authService.forgotPassword(accountVo), HttpStatus.OK);
    }

//    @GetMapping("/users")
//    public ResponseEntity<?> findUser(@RequestParam("username") String username) throws Exception {
//        return new ResponseEntity<>(authService.findUserByUsername(username), HttpStatus.OK);
//    }

    @GetMapping("RoleAndPermission")
    public ResponseEntity<?> getRoleAndPermissionByUsername(@RequestParam String username) throws Exception {
        return new ResponseEntity<>(this.authService.getRoleAndPermissionByUsername(username), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("ID hoặc mật khẩu không đúng !", e);
        }
    }

    @GetMapping("GenerateJWT")
    public ResponseEntity<?> generateJWT(@RequestParam String username) throws Exception {
        return new ResponseEntity<>(this.authService.generateJWT(username), HttpStatus.OK);
    }
}
