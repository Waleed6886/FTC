package com.example.ftc.ftc.View;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


import com.example.ftc.ftc.API.RemoteDataSource;
import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Login.User;
import com.example.ftc.ftc.R;

import io.realm.Realm;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mPhoneNumberEditText;
//    private View mProgressView;
//    private View mLoginFormView;
    static RemoteDataSource remoteDataSource = new RemoteDataSource();
    static Authenticator authenticator = new Authenticator();
    String code;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        // Set up the login form.
        mPhoneNumberEditText = findViewById(R.id.phoneNumber);

        Button mPhoneSignInButton = findViewById(R.id.next_button);
        mPhoneSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void attemptLogin() {

        // Reset errors.
        mPhoneNumberEditText.setError(null);

        // Store values at the time of the login attempt.
        String phoneNumber = mPhoneNumberEditText.getText().toString();
        User user =new User();
        user.setMobile(phoneNumber);
        authenticator.setUser(user);
        remoteDataSource.sendMobileNumber(authenticator.getUser().getMobile());

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberEditText.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumberEditText;
            cancel = true;
        } else if (!isPhoneNumberValid(phoneNumber)) {
            mPhoneNumberEditText.setError(getString(R.string.error_invalid_phoneNumber));
            focusView = mPhoneNumberEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            receiveCode();

        }
    }

    public void receiveCode(){
        Intent intent = new Intent(this, ReceiveCodeActivity.class);
        startActivity(intent);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //TODO: Replace this with your own logic
        return phoneNumber.contains("+966");
    }
}

