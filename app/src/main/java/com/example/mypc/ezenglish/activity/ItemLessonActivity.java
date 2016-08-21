package com.example.mypc.ezenglish.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.MyPagerAdapter;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.UtilFunctions;

/**
 * Created by MyPC on 19/08/2016.
 */
public class ItemLessonActivity extends FragmentActivity {
    ViewPager viewPager;
    AHBottomNavigation bottomNavigation;
    FragmentPagerAdapter adapterViewPager;
    LinearLayout linearLayoutPlayer;
    Lesson l;
    RealmLeason rl;
    static Context context;
    int location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlesson);
        context = this;
        init();
    }

    private void init() {
        Intent i = getIntent();
        int id = i.getIntExtra("id", 1);
        Log.e("anh yeu em ", "" + id);
        rl = new RealmLeason(this);
        l = rl.getleassongbyid(id);
        getViews();
        setListeners();
    }

    public void getViews() {
        linearLayoutPlayer = (LinearLayout) findViewById(R.id.linearLayoutPlayer);
        Bitmap albumArt = UtilFunctions.blur(l.getImg(), context);
        linearLayoutPlayer.setBackgroundDrawable(new BitmapDrawable(albumArt));


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), l);
        viewPager.setAdapter(adapterViewPager);


        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_maps_local_bar, R.color.color_tab_2);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_music, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_maps_local_restaurant, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_maps_local_attraction, R.color.color_tab_4);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_content_add, R.color.color_tab_5);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
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

        location = 1;
        bottomNavigation.setCurrentItem(1);
        viewPager.setCurrentItem(1);
    }

    public void setListeners() {

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                location = position;
                viewPager.setCurrentItem(position);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getApplicationContext());
        if (isServiceRunning) {
            if (location == 0) {
                viewPager.setCurrentItem(0);

            }
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            location = position;
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
