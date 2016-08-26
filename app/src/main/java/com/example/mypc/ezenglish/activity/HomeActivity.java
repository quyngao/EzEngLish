package com.example.mypc.ezenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.controls.RealmController;
import com.example.mypc.ezenglish.flagment.HomeFragment;
import com.example.mypc.ezenglish.flagment.ScheduleFragment;
import com.example.mypc.ezenglish.util.Constant;
import com.firebase.client.Firebase;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import io.realm.Realm;

/**
 * Created by Quylt on 8/26/2016.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemlearnNow;
    private ResideMenuItem itemlogout;
    private ResideMenuItem itemguide;
    private ResideMenuItem itemsetting;
    private HomeActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        setUpMenu();
        if (savedInstanceState == null)
            changeFragment(new HomeFragment());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrophome));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarhome);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbarhome);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void setUpMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.default_album_art_thumb);
        resideMenu.attachToActivity(this);

        resideMenu.setScaleValue(0.6f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        // create menu items;
        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "Profile");
        itemlearnNow = new ResideMenuItem(this, R.drawable.icon_calendar, "Learn Now");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
        itemsetting = new ResideMenuItem(this, R.drawable.icon_settings, "Setting");
        itemguide = new ResideMenuItem(this, R.drawable.icon_profile, "Guide");
        itemlogout = new ResideMenuItem(this, R.drawable.icon_profile, "Logout");

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemlearnNow.setOnClickListener(this);
        itemsetting.setOnClickListener(this);
        itemguide.setOnClickListener(this);
        itemlogout.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemlearnNow, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemsetting, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemguide, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemlogout, ResideMenu.DIRECTION_LEFT);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if (view == itemHome) {
            changeFragment(new HomeFragment());
        } else if (view == itemProfile) {
//            changeFragment(new ProfileFragment());
        } else if (view == itemCalendar) {
            changeFragment(new ScheduleFragment());
        } else if (view == itemlearnNow) {
            startActivity(new Intent(HomeActivity.this, LessonActivity.class));
            finish();
        } else if (view == itemlogout) {
            Firebase ref = new Firebase(Constant.FIREBASE_USER_URL);
            ref.unauth();
            finish();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
        resideMenu.closeMenu();
    }

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public ResideMenu getResideMenu() {
        return resideMenu;
    }
}
