package pidvn.auth.models;

public class JwtResponse {
    private final String accessToken;
    private final String tokenType = "Bearer";
    private final String tokenHeader = "Authorization";

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }
}
