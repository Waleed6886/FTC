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
    TextView postTitle, postCategory, postDescription, postWorkingHours, postLocation;
    ImageView postImage;
    Post post;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        int postID = Integer.parseInt(getIntent().getStringExtra("post"));
        post = PostAdapter.PostList.get(postID);
        initializeViews();
        Log.d("Displaying Post Object",post.getMetadata().getName());
        setViews(post);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    private void setViews(Post post) {
        postTitle.setText(post.getMetadata().getName());
        postCategory.setText(post.getMetadata().getType()); //TODO: MAKE TYPE RETURN STRING WITH THE NAME OF THE CATEGORY
        postDescription.setText(post.getDescription());
        postWorkingHours.setText(post.getMetadata().getWorkingHours());
        Picasso.get()
                .load(post.getMetadata().getImgPath())
                .into(postImage);

    }

    private void initializeViews() {
        postTitle=findViewById(R.id.postDetailsTitle);
        postCategory=findViewById(R.id.postDetailsCategory);
        postDescription=findViewById(R.id.postDetailsDesc);
        postWorkingHours=findViewById(R.id.postDetailsWorkingHours);
        postImage=findViewById(R.id.postDetailsImg);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng foodTruckLocation = new LatLng(post.getLatitude(), post.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(foodTruckLocation)
                .title(post.getMetadata().getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(foodTruckLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(post.getLatitude(), post.getLongitude()), 12.0f));    }
}
