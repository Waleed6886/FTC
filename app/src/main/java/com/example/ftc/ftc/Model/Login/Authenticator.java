package com.example.ftc.ftc.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authenticator {
    @SerializedName("user")
    @Expose
    private static User user;
    @SerializedName("access_token")
    @Expose
    private String accessToken;

    public static User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
