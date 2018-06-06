package com.example.redbaron.towntoday;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Context context;
    private FragmentManager fragmentManager;

    public void bottomNavClicked(View v){
        switch (v.getId()){
            case R.id.bar_left_img:
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                SupportMapFragment mapFragment = new SupportMapFragment();
                fragmentTransaction1.replace(R.id.fragment_area, mapFragment);
                fragmentTransaction1.commit();
                break;
            case R.id.bar_center_img:
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                NetflixFragment netflixFragment = new NetflixFragment();
                fragmentTransaction2.replace(R.id.fragment_area, netflixFragment);
                fragmentTransaction2.commit();
                findViewById(R.id.container).setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                break;
            case R.id.bar_right_img:
                //Favorite Activity
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_area, new CategoriesFragment());
        ft.commit();

        context = this;
    }

}
