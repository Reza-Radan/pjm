package com.progetto.projectmanagement.security;

public class SecurityModel {
    String accessToken ;

    public SecurityModel() {
    }

    public SecurityModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
