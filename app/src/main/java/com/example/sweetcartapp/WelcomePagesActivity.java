package com.example.sweetcartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sweetcartapp.ShoppersRoom.ShoppersStop;
import com.example.sweetcartapp.ViewPagerAdapter.ZoomOutPageTransformer;
import com.example.sweetcartapp.ViewPagerAdapter.mViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class WelcomePagesActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_pages);

        // Add a view pager for better UI/UX
        setMyViewPager();
    }

    private void setMyViewPager() {
        viewPager=findViewById(R.id.startview_pager);
        viewPager.setAdapter(new mViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

    }

    public void setCurrentItem(int item, boolean smoothscroller) {
        viewPager.setCurrentItem(item, smoothscroller);
    }


}