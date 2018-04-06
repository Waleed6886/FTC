package com.example.ftc.ftc.View;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ftc.ftc.R;

public class EditProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ImageButton imageButton;
    private EditText mName;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageButton = findViewById(R.id.userImageUpdate);
        mName = findViewById(R.id.userNameUpdate);
        mEmail = findViewById(R.id.userEmilUpdate);

        updateInformation();

    }

    public void updateInformation(){

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
