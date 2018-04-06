package com.example.ftc.ftc.API;

import android.util.Log;

import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Login.User;
import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.View.LoginActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSource  {


    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://elmhackhub.com/")
            .client(okBuilder.build())
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    DataSource dataSource = retrofit.create(DataSource.class);
    Call<List<Post>> postListCall = dataSource.getPost();

    GetResponse getResponse;
    public void getPostListCall(final GetPost getPost){
        postListCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                getPost.passPostList(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    public void sendMobileNumber(String mobile) {
        Boolean authenticated = false;
        dataSource.sendNumber(mobile).enqueue(new Callback<Authenticator>() {
            @Override
            public void onResponse(Call<Authenticator> call, Response<Authenticator> response) {
                Log.e("RemoteDataSource", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<Authenticator> call, Throwable t) {

            }
        });
    }

    public void sendSMS(String mobile,String code) {
        dataSource.sendVerification(mobile,code).enqueue(new Callback<Authenticator>() {
            @Override
            public void onResponse(Call<Authenticator> call, Response<Authenticator> response) {
                Authenticator authenticator = response.body();

            }

            @Override
            public void onFailure(Call<Authenticator> call, Throwable t) {

            }
        });
    }

}
