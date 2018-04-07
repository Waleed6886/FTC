package com.example.ftc.ftc.Model.Login;

import com.example.ftc.ftc.Model.Login.Role;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class User extends RealmObject{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("email")
    @Expose
    private Boolean email;
    @SerializedName("email_activated")
    @Expose
    private boolean emailActivated;
    @SerializedName("mobile_activated")
    @Expose
    private boolean mobileActivated;
    @SerializedName("role")
    @Expose
    private Role role;
    @SerializedName("url")
    @Expose
    private String url;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public boolean isEmailActivated() {
        return emailActivated;
    }

    public void setEmailActivated(boolean emailActivated) {
        this.emailActivated = emailActivated;
    }

    public boolean isMobileActivated() {
        return mobileActivated;
    }

    public void setMobileActivated(boolean mobileActivated) {
        this.mobileActivated = mobileActivated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
