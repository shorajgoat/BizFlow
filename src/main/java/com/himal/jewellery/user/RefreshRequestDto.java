package com.himal.jewellery.user;

public class RefreshRequestDto {

    private String refreshToken;

    public RefreshRequestDto() {}

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}