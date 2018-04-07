package com.example.ftc.ftc.API;

import android.util.Log;
import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Post;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSource  {

    public String TAG ="RemoteDataSource";
    Realm realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
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


    public void sendMobileNumber(final String mobile) {
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
    
    public void sendSMS(final String mobile, final String code) {
        dataSource.sendVerification(mobile,code).enqueue(new Callback<Authenticator>() {
            @Override
            public void onResponse(Call<Authenticator> call, Response<Authenticator> response) {
                save_data_to_database(mobile,code);

            }

            @Override
            public void onFailure(Call<Authenticator> call, Throwable t) {

            }
        });
    }

    private void save_data_to_database(final String mobile, final String code) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Authenticator authenticator = bgRealm.createObject(Authenticator.class);
                authenticator.getUser().setMobile(mobile);
                authenticator.getUser().setCode(code);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: you did it :)");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: ops something went wrong :(");
            }
        });



    }





    GetResponse getResponse;
    public void getPostListCall(final GetPost getPost,String token){
        Call<List<Post>> postCall = dataSource.getPost("bearer "+token);
        postCall.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body();
                    getPost.passPostList(posts);
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.e("failed", "onFailure: ");

                }
            });
    }

}
