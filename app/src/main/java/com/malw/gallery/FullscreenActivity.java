package com.malw.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class FullscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FullscreenPagerAdapter(getSupportFragmentManager(), getIntent().getParcelableArrayListExtra("images")));
        viewPager.setCurrentItem(getIntent().getIntExtra("image_index", 0));
    }

}

