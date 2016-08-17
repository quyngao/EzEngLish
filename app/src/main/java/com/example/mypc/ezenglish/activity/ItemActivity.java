package com.example.mypc.ezenglish.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.ViewPagerAdapter;
import com.example.mypc.ezenglish.flagment.Mp3Flagment;
import com.example.mypc.ezenglish.flagment.OneFragment;
import com.example.mypc.ezenglish.flagment.TextFlagment;

/**
 * Created by Quylt on 8/16/2016.
 */
public class ItemActivity extends AppCompatActivity {
    static private ViewPager viewPager;
    AHBottomNavigation bottomNavigation;
    static Mp3Flagment fragment1;
    TextFlagment flagment2;
    static ViewPagerAdapter adapter;

    public static void changeButton() {
        fragment1.changeUI();
        if (adapter != null) adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragment1 = new Mp3Flagment();
        flagment2 = new TextFlagment();
        setupViewPager(viewPager);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_maps_place, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_maps_local_bar, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_maps_local_restaurant, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_maps_local_attraction, R.color.color_tab_4);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_content_add, R.color.color_tab_5);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setAccentColor(Color.parseColor("#C5CAE9"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setColored(true);
        bottomNavigation.setCurrentItem(2);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(fragment1, "ONE");
        adapter.addFragment(flagment2, "TWO");
        adapter.addFragment(new OneFragment(R.layout.welcome_slide2), "TWO");
        adapter.addFragment(new OneFragment(R.layout.welcome_slide3), "THREE");
        adapter.addFragment(new OneFragment(R.layout.welcome_slide4), "four");
        adapter.addFragment(new OneFragment(R.layout.welcome_slide1), "five");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            bottomNavigation.setCurrentItem(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}
