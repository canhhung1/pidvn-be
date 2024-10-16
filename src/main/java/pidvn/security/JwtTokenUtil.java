package pidvn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pidvn.auth.models.AccountVo;
import pidvn.auth.services.AuthService;
import pidvn.entities.one.Users;
import pidvn.repositories.one.UsersRepo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AuthService authService;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) throws Exception {

        AccountVo user = this.authService.getUserInfo(userDetails.getUsername()).get(0);

        Map rolesPermissions = this.authService.getRoleAndPermissionByUsername(userDetails.getUsername());

        // Add some user's info to token
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getId());
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

        return this.doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
