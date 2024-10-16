package com.other.app.JwtAuthentication.Model;

public class RefreshTokenRequest {
	private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
