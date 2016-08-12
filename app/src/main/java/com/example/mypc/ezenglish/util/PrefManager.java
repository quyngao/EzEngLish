package com.example.mypc.ezenglish.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Quylt on 8/12/2016.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "isfirst";
    private static final String SOUNDALARM = "sound";
    private static final String ISALARM = "isalarm";

    private static final String STEP = "step";


    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setstep(int step) {
        editor.putInt(STEP, step);
        editor.commit();
    }

    public int getstep() {
        return pref.getInt(STEP, 5);
    }

    public void setsound(boolean sound) {
        editor.putBoolean(SOUNDALARM, sound);
        editor.commit();
    }

    public boolean getsound() {
        return pref.getBoolean(SOUNDALARM, true);
    }

    public void setalarm(boolean sound) {
        editor.putBoolean(ISALARM, sound);
        editor.commit();
    }

    public boolean getalarm() {
        return pref.getBoolean(ISALARM, true);
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


}
