package in.dotworld.payload;

public class JwtLoginResponse {

	private String token;
	private String tokenType = "Bearer";

	public JwtLoginResponse(String token) {
		this.token = token;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
