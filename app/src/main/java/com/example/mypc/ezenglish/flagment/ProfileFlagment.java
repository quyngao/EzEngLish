package com.example.mypc.ezenglish.flagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.HomeActivity;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.User;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.realm.RealmUser;
import com.example.mypc.ezenglish.util.PrefManager;
import com.special.ResideMenu.ResideMenu;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

/**
 * Created by MyPC on 28/08/2016.
 */
public class ProfileFlagment extends Fragment {
    private View parentView;
    private ResideMenu resideMenu;
    TextView tv_name, tv_inforperson, tv_inforlesson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_profile, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        HomeActivity parentActivity = (HomeActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        PrefManager prefManager = new PrefManager(parentActivity);
        RealmLeason rl = new RealmLeason(parentActivity);
        User user = new RealmUser(parentActivity).getUser();
        System.out.println("" + user.getName());

        tv_name = (TextView) parentView.findViewById(R.id.tv_pr_name);
        tv_inforperson = (TextView) parentView.findViewById(R.id.tv_inforpe);
        tv_inforlesson = (TextView) parentView.findViewById(R.id.tv_inforlearn);


        tv_name.setText("Hello  " + user.getName());
        tv_inforperson.setText("Description :" + user.getDescription());
        int i = prefManager.getlearnid();
        String s = "LEARNING :";
        if (i != 0) {
            Lesson l = rl.getleassongbyid(prefManager.getlearnid());
            s = s + l.getName();
            tv_inforlesson.setText("Total Lesson : 4/7" + " \n Total time : 12h" + " Begin" + l.getStart() + " \n " + s);

        } else tv_inforlesson.setText("Notthing  ..");
        StackedBarChart mStackedBarChart = (StackedBarChart) parentView.findViewById(R.id.stackedbarchart);
        StackedBarModel s1 = new StackedBarModel("Day of Death");
        s1.addBar(new BarModel(2f, getResources().getColor(R.color.color_audio)));
        s1.addBar(new BarModel(4.3f, getResources().getColor(R.color.color_voca)));
        s1.addBar(new BarModel(4f, getResources().getColor(R.color.color_story)));


        StackedBarModel s2 = new StackedBarModel("Bubba's ");
        s2.addBar(new BarModel(2.2f, getResources().getColor(R.color.color_audio)));
        s2.addBar(new BarModel(3.3f, getResources().getColor(R.color.color_voca)));
        s2.addBar(new BarModel(5f, getResources().getColor(R.color.color_story)));

        StackedBarModel s3 = new StackedBarModel("A Kiss");

        s3.addBar(new BarModel(1.5f, getResources().getColor(R.color.color_audio)));
        s3.addBar(new BarModel(2.3f, getResources().getColor(R.color.color_voca)));
        s3.addBar(new BarModel(4f, getResources().getColor(R.color.color_story)));

        StackedBarModel s4 = new StackedBarModel("Changed");
        s4.addBar(new BarModel(1.2f, getResources().getColor(R.color.color_audio)));
        s4.addBar(new BarModel(1.0f, getResources().getColor(R.color.color_voca)));
        s4.addBar(new BarModel(2f, getResources().getColor(R.color.color_story)));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();

    }

}
