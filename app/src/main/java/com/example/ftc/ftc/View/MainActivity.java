package com.example.ftc.ftc.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ftc.ftc.API.GetPost;
import com.example.ftc.ftc.API.RemoteDataSource;
import com.example.ftc.ftc.Model.Login.User;
import com.example.ftc.ftc.Model.Metadata;
import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.R;
import com.squareup.picasso.Picasso;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GetPost {
    List<Post> listofPost= new ArrayList<>();
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    private void init() {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        remoteDataSource.getPostListCall(MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        postAdapter = new PostAdapter(listofPost,this);
        RecyclerView recyclerView =findViewById(R.id.rec1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postAdapter);
    }

    public void addPost(){
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            displayProfile();
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayProfile(){
        Intent intent = new Intent(this, DisplayProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void passPostList(final List list) {
        listofPost = list;
        postAdapter.update(list);
    }

    public void displatUserInfo(User user){
        ImageView profilePic=findViewById(R.id.imageView);
        TextView userName=findViewById(R.id.userName);
        TextView phoneNumber = findViewById(R.id.phoneNumber);

        String name ="";
        if(user!=null&&user.getFullName() !=null)
            name=user.getFullName();
        else
            name="User";
        userName.setText(name);
        if(user !=null && user.getMobile()!=null)
            phoneNumber.setText(user.getMobile());
        else
            phoneNumber.setText("+966568484248");
        try {


            if (user != null && user.getUrl() != null)
                Picasso.get().load(user.getUrl()).into(profilePic);
        }catch (Exception e){
            Log.e("picasso",e.getMessage());
        }

    }


}
