package com.example.ftc.ftc.API;

import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Login.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataSource {

    @POST("/api/v1/users")
    @FormUrlEncoded
    Call<Authenticator> sendNumber(@Field("mobile") String mobile);

    @POST("/api/v1/auth")
    @FormUrlEncoded
    Call<Authenticator> sendVerification(@Field("mobile") String mobile, @Field("code") String verificationCode);

}
