package com.example.ftc.ftc.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Login.User;
import com.example.ftc.ftc.R;
import com.squareup.picasso.Picasso;

public class DisplayProfileActivity extends AppCompatActivity {
    TextView name,email,phoneNumber;
    ImageView profilePic;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        user= new User();
        user = Authenticator.getUser();
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
//            user.setFullName("Name");
//            user.setEmail("Email");
//            user.setMobile("Phone Number");

        name.setText("blah blah");
        email.setText("blah blah");
        phoneNumber.setText("blah blah");
        if( user != null && !user.getUrl().isEmpty()) {
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
