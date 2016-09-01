package com.example.mypc.ezenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.dialog.RemidDialog;
import com.example.mypc.ezenglish.flagment.GuideFragment;
import com.example.mypc.ezenglish.flagment.HomeFragment;
import com.example.mypc.ezenglish.flagment.ProfileFlagment;
import com.example.mypc.ezenglish.flagment.ScheduleFragment;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.realm.RealmUser;
import com.example.mypc.ezenglish.util.Constant;
import com.example.mypc.ezenglish.util.PrefManager;
import com.firebase.client.Firebase;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

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
    Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_tmp_menu);
        initCollapsingToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        }
        return (super.onOptionsItemSelected(menuItem));
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
                    collapsingToolbar.setTitle("");
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
        itemHome = new ResideMenuItem(this, R.drawable.icon_tmp_home, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_tmp_profile, "Profile");
        itemlearnNow = new ResideMenuItem(this, R.drawable.icon_tmp_learning, "Learn Now");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_tmp_calender, "Calendar");
        itemsetting = new ResideMenuItem(this, R.drawable.icon_tmp_setting, "Setting");
        itemguide = new ResideMenuItem(this, R.drawable.icon_tmp_guide, "Guide");
        itemlogout = new ResideMenuItem(this, R.drawable.icon_tmp_logout, "Logout");

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
            changeFragment(new ProfileFlagment());
        } else if (view == itemCalendar) {
            changeFragment(new ScheduleFragment());
        } else if (view == itemlearnNow) {
            int id = new PrefManager(HomeActivity.this).getlearnid();
            if (id == 0) {
                startActivity(new Intent(HomeActivity.this, LessonActivity.class));
                finish();
            } else {
                Intent i = new Intent(HomeActivity.this, ItemLessonActivity.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }

        } else if (view == itemsetting) {
            int id = new PrefManager(HomeActivity.this).getlearnid();
            RealmLeason rl = new RealmLeason(HomeActivity.this);
            if (id == 0) {
                Toast.makeText(HomeActivity.this, "nothing to setting", Toast.LENGTH_SHORT).show();
            } else {
                Lesson l = rl.getleassongbyid(id);
                RemidDialog reddialog = new RemidDialog(HomeActivity.this, l);
                reddialog.show();
            }
        } else if (view == itemguide) {
            changeFragment(new GuideFragment());
            Log.e("ok", "ok");
        } else if (view == itemlogout) {
            Firebase ref = new Firebase(Constant.FIREBASE_USER_URL);
            ref.unauth();
            RealmUser realmUser = new RealmUser(HomeActivity.this);
            realmUser.deleteUser();
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
