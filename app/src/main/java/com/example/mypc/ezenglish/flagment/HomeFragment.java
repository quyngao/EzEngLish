package com.example.mypc.ezenglish.flagment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.activity.HomeActivity;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.LessonAdapter;
import com.example.mypc.ezenglish.model.Topic;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.special.ResideMenu.ResideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quylt on 8/26/2016.
 */
public class HomeFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        HomeActivity parentActivity = (HomeActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
    }

}
