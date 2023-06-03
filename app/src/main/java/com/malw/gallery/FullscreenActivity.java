package com.malw.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class FullscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FullscreenPagerAdapter(getSupportFragmentManager(), getIntent().getParcelableArrayListExtra("images")));
        viewPager.setCurrentItem(getIntent().getIntExtra("image_index", 0));
    }
    public void onClick(View view) {
        startActivity(new Intent(FullscreenActivity.this, MainActivity.class));}
}


