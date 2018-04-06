package com.example.ftc.ftc.View;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.ftc.ftc.R;

public class AddPostActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mCategory;
    private EditText mDescription;
    private EditText mWorkingHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        mName = findViewById(R.id.name);
        mCategory = findViewById(R.id.category);
        mDescription = findViewById(R.id.description);
        mWorkingHours = findViewById(R.id.workingHours);

        Button mImageGallery = findViewById(R.id.uploadImageGallery);
        mImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        Button mImageCamera = findViewById(R.id.uploadImageCamera);
        mImageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });

    }

}
