package com.example.ftc.ftc.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Login.User;
import com.example.ftc.ftc.R;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayProfileActivity extends AppCompatActivity {
    TextView name,email,phoneNumber;
    ImageView profilePic;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        user= new User();
        Realm realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        RealmResults<Authenticator> authenticators=realm.where(Authenticator.class).findAll();
        Log.e("authenticators.size()",authenticators.size()+"");
//        user =authenticators.get(0).getUser();
        initializeViews();
        setViews();

    }

    private void initializeViews() {
    name= findViewById(R.id.userName);
    email = findViewById(R.id.Useremail);
    phoneNumber = findViewById(R.id.UserPhoneNumber);
    profilePic = findViewById(R.id.user_profile_photo);
    }

    private void setViews() {

        name.setText("Nawaf Alquaid");
        email.setText("nnawif@gmail.com");
        phoneNumber.setText("+966568484248");
        if( user != null && user.getUrl() != null && !user.getUrl().isEmpty()) {
            Picasso.get()
                    .load(user.getUrl())
                    .into(profilePic);
        }
    }





    private void editProfile(){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }


}
