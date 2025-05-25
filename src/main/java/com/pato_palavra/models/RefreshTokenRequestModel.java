package com.pato_palavra.models;

public class RefreshTokenRequestModel {
    private String refreshToken;

    public RefreshTokenRequestModel() {
    }

    public RefreshTokenRequestModel(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
