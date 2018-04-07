package com.example.ftc.ftc.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ftc.ftc.Model.Login.Authenticator;
import com.example.ftc.ftc.Model.Metadata;
import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.R;

import io.realm.Realm;
import pl.aprilapps.easyphotopicker.EasyImage;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class AddPostActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mCategory;
    private EditText mDescription;
    private EditText mWorkingHours;
    Realm realm;
    private String TAG = "AddPostActivity";

    private Location getLastBestLocation() {
        LocationManager mLocationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location Permissions denied", Toast.LENGTH_LONG).show();
            return null;
        }
        assert mLocationManager != null;
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        long GPSLocationTime = 0;
        if (null != locationGPS) {
            GPSLocationTime = locationGPS.getTime();
        }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if (0 < GPSLocationTime - NetLocationTime) {
            return locationGPS;
        } else {
            return locationNet;
        }
    }

    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        realm = Realm.getDefaultInstance();


        mName = findViewById(R.id.name);
        mCategory = findViewById(R.id.category);
        mDescription = findViewById(R.id.description);
        mWorkingHours = findViewById(R.id.workingHours);

        final Button mImageGallery = findViewById(R.id.uploadImageGallery);
        mImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType("image/*");
                EasyImage.openGallery(AddPostActivity.this, 1);
//                startActivityForResult(photoPickerIntent, 1);
//            }
//        });


                final Button mImageCamera = findViewById(R.id.uploadImageCamera);
                mImageCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        EasyImage.openCamera(AddPostActivity.this, 0);
//                startActivityForResult(takePicture, 0);
//            }
//        });

                        Button getLocation = findViewById(R.id.location);
                        getLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                location = getLastBestLocation();
                                if (location != null)
                                    Log.d("check Location", location.getLatitude() + "");
                            }
                        });
                        Button submit = findViewById(R.id.submit);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm bgRealm) {
                                        Post post = bgRealm.createObject(Post.class);
                                        post.getMetadata().setName(mName.getText().toString());

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
                        });

                    }

                });
            }
        });
    }
}
