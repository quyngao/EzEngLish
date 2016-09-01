package com.example.mypc.ezenglish.flagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.HomeActivity;
import com.special.ResideMenu.ResideMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Quylt on 8/26/2016.
 */
public class ScheduleFragment extends Fragment {
    private View parentView;
    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_schedule, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        HomeActivity parentActivity = (HomeActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        WeekView mWeekView = (WeekView) parentView.findViewById(R.id.weekView);
        mWeekView.goToToday();
        mWeekView.setNumberOfVisibleDays(3);
        mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                return getlistevent(newMonth, newYear);
            }
        });
    }

    public List<WeekViewEvent> getlistevent(int x, int y) {
        int newMonth = x;
        int newYear = y;
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 7);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "Listen Audio", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_audio));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(2, "Listen Voca ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_voca));
        events.add(event);


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(4, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 22);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        startTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(5, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);

        Calendar c = Calendar.getInstance();
        int dayofmoth = c.get(Calendar.DAY_OF_MONTH);

        dayofmoth++;


        startTime = c.getInstance();
        startTime.set(c.DAY_OF_MONTH, dayofmoth);
        startTime.set(c.HOUR_OF_DAY, 7);
        startTime.set(c.MINUTE, 0);
        startTime.set(c.MONTH, newMonth - 1);
        startTime.set(c.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(1, "Listen Voca", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_voca));
        events.add(event);

        startTime = c.getInstance();
        startTime.set(c.DAY_OF_MONTH, dayofmoth);

        startTime.set(c.HOUR_OF_DAY, 12);
        startTime.set(c.MINUTE, 0);
        startTime.set(c.MONTH, newMonth - 1);
        startTime.set(c.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(c.HOUR, 2);
        endTime.set(c.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);


        startTime = c.getInstance();
        startTime.set(c.DAY_OF_MONTH, dayofmoth);

        startTime.set(c.HOUR_OF_DAY, 17);
        startTime.set(c.MINUTE, 0);
        startTime.set(c.MONTH, newMonth - 1);
        startTime.set(c.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(c.HOUR, 2);
        endTime.set(c.MONTH, newMonth - 1);
        event = new WeekViewEvent(4, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);


        startTime = c.getInstance();
        startTime.set(c.DAY_OF_MONTH, dayofmoth);

        startTime.set(c.HOUR_OF_DAY, 22);
        startTime.set(c.MINUTE, 0);
        startTime.set(c.MONTH, newMonth - 1);
        startTime.set(c.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(c.HOUR, 1);
        startTime.set(c.MINUTE, 40);
        endTime.set(c.MONTH, newMonth - 1);
        event = new WeekViewEvent(5, "Listen Story ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_story));
        events.add(event);

        startTime = c.getInstance();
        startTime.set(c.DAY_OF_MONTH, dayofmoth);

        startTime.set(c.HOUR_OF_DAY, 22);
        startTime.set(c.MINUTE, 45);
        startTime.set(c.MONTH, newMonth - 1);
        startTime.set(c.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(c.HOUR, 0);
        startTime.set(c.MINUTE, 30);
        endTime.set(c.MONTH, newMonth - 1);
        event = new WeekViewEvent(6, "Listen Audio ", startTime, endTime);
        event.setColor(getResources().getColor(R.color.color_audio));
        events.add(event);

        dayofmoth++;

        for (int i = 0; i < 6; i++) {
            startTime = c.getInstance();
            startTime.set(c.DAY_OF_MONTH, dayofmoth);
            startTime.set(c.HOUR_OF_DAY, 7);
            startTime.set(c.MINUTE, 0);
            startTime.set(c.MONTH, newMonth - 1);
            startTime.set(c.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR, 1);
            endTime.set(Calendar.MONTH, newMonth - 1);
            event = new WeekViewEvent(1, "Listen Voca", startTime, endTime);
            event.setColor(getResources().getColor(R.color.color_voca));
            events.add(event);

            startTime = c.getInstance();
            startTime.set(c.DAY_OF_MONTH, dayofmoth);

            startTime.set(c.HOUR_OF_DAY, 12);
            startTime.set(c.MINUTE, 0);
            startTime.set(c.MONTH, newMonth - 1);
            startTime.set(c.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(c.HOUR, 2);
            endTime.set(c.MONTH, newMonth - 1);
            event = new WeekViewEvent(3, "Listen Story ", startTime, endTime);
            event.setColor(getResources().getColor(R.color.color_story));
            events.add(event);


            startTime = c.getInstance();
            startTime.set(c.DAY_OF_MONTH, dayofmoth);

            startTime.set(c.HOUR_OF_DAY, 17);
            startTime.set(c.MINUTE, 0);
            startTime.set(c.MONTH, newMonth - 1);
            startTime.set(c.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(c.HOUR, 2);
            endTime.set(c.MONTH, newMonth - 1);
            event = new WeekViewEvent(4, "Listen Story ", startTime, endTime);
            event.setColor(getResources().getColor(R.color.color_story));
            events.add(event);


            startTime = c.getInstance();
            startTime.set(c.DAY_OF_MONTH, dayofmoth);

            startTime.set(c.HOUR_OF_DAY, 22);
            startTime.set(c.MINUTE, 0);
            startTime.set(c.MONTH, newMonth - 1);
            startTime.set(c.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(c.HOUR, 1);
            startTime.set(c.MINUTE, 40);
            endTime.set(c.MONTH, newMonth - 1);
            event = new WeekViewEvent(5, "Listen Story ", startTime, endTime);
            event.setColor(getResources().getColor(R.color.color_story));
            events.add(event);

            startTime = c.getInstance();
            startTime.set(c.DAY_OF_MONTH, dayofmoth);

            startTime.set(c.HOUR_OF_DAY, 22);
            startTime.set(c.MINUTE, 45);
            startTime.set(c.MONTH, newMonth - 1);
            startTime.set(c.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(c.HOUR, 0);
            startTime.set(c.MINUTE, 30);
            endTime.set(c.MONTH, newMonth - 1);
            event = new WeekViewEvent(6, "Listen Audio ", startTime, endTime);
            event.setColor(getResources().getColor(R.color.color_audio));
            events.add(event);
            dayofmoth++;
        }


        return events;
    }
}
