package com.example.ftc.ftc.View;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by Nawif on 4/6/18.
 */

/*
TODO:MAKE A POST HAVE MORE THAN ONE IMAGE AND ENHANCE UI
 */

public class PostDetails extends AppCompatActivity implements OnMapReadyCallback {
    TextView postTitle, postCategory, postDescription, postWorkingHours;
    ImageView postImage;
    public static final String POST_KEY = "POST";
    Bundle extras;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        init();
    }

    private void init() {
        initializeViews();
        setViews();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

        private void setViews (){
               extras = getIntent().getExtras();
            if (extras != null) {
                Post postData = extras.getParcelable(POST_KEY);

                if (postData != null) {
                    postTitle.setText(postData.getMetadata().getName());
                 //   postCategory.setText(postData.getMetadata().getType()); //TODO: MAKE TYPE RETURN STRING WITH THE NAME OF THE CATEGORY
                    postDescription.setText(postData.getDescription());
                    postWorkingHours.setText(postData.getMetadata().getWorkingHours());
                    Picasso.get()
                            .load(postData.getMetadata().getImgPath())
                            .into(postImage);
                }

            }
        }

        private void initializeViews () {
            postTitle = findViewById(R.id.postDetailsTitle);
            postCategory = findViewById(R.id.postDetailsCategory);
            postDescription = findViewById(R.id.postDetailsDesc);
            postWorkingHours = findViewById(R.id.postDetailsWorkingHours);
            postImage = findViewById(R.id.postDetailsImg);
        }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        extras = getIntent().getExtras();
        if (extras != null) {
            Post postData = extras.getParcelable(POST_KEY);
        LatLng foodTruckLocation = new LatLng(postData.getLatitude(), postData.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(foodTruckLocation)
                .title(postData.getMetadata().getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(foodTruckLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(postData.getLatitude(), postData.getLongitude()), 12.0f));    }
     }
}
