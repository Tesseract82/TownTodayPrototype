package com.example.redbaron.towntoday;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class NetflixActivity extends AppCompatActivity {

    private Context c;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map2:
                    return true;
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_heart2:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix);
        LinearLayout mainLinear = findViewById(R.id.netflixMainLinear);

        Constants.initializeEventThumbs(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        c = this;
        int testAdder = 0;

        for(String key : Constants.eventThumbs.keySet()){
            if(!Constants.selected.contains(key)){
                continue;
            }
            TextView categoryTitle = new TextView(this);
            categoryTitle.setText(key.toUpperCase());
            categoryTitle.setTextSize(25);
            categoryTitle.setTextColor(Color.rgb(237, 197, 37));
            mainLinear.addView(categoryTitle);

            LinearLayout linear2 = new LinearLayout(this);
            linear2.setOrientation(LinearLayout.HORIZONTAL);

            for(EventThumbView etv : Constants.eventThumbs.get(key)){
                etv.setPadding(2 * (int) Constants.dpPadding, 2 * (int) Constants.dpPadding,
                        2 * (int) Constants.dpPadding, 2 * (int) Constants.dpPadding);
                etv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent eventIntent = new Intent(c, EventActivity.class);
                        startActivity(eventIntent);
                    }
                });
                linear2.addView(etv);
            }
            HorizontalScrollView sv = new HorizontalScrollView(this);
            sv.addView(linear2);
            mainLinear.addView(sv);
        }
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
}
