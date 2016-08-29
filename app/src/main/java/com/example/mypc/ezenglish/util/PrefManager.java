package com.example.mypc.ezenglish.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mypc.ezenglish.model.VocaData;
import com.example.mypc.ezenglish.model.VocaRed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quylt on 8/12/2016.
 */
public class PrefManager {
    SharedPreferences pref;
    private static Gson gson;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "isfirst";
    private static final String VOCARED = "VOCARED";
    private static final String LISTVOCA = "LISTVOCA";
    private static final String ISREMOTE = "ISREMOTE";


    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public void setVocared(VocaRed v) {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        String s = gson.toJson(v);
        editor.putString(VOCARED, s);
        editor.commit();

    }

    public void setlistvoca(List<VocaData> v) {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        String s = gson.toJson(v);
        editor.putString(LISTVOCA, s);
        editor.commit();

    }

    public List<VocaData> getlistvoca() {
        List<VocaData> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        String vcr = pref.getString(LISTVOCA, "");
        if (vcr.equalsIgnoreCase("")) {
            return list;
        } else {
            try {
                list = new Gson().fromJson(vcr, new TypeToken<List<VocaData>>() {
                }.getType());
                return list;
            } catch (Exception ex) {
                ex.printStackTrace();
                return list;
            }
        }
    }

    public VocaRed getVocaRed() {
        VocaRed vc = new VocaRed();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        String vcr = pref.getString(VOCARED, "");
        if (vcr.equalsIgnoreCase("")) {
            return vc;
        } else {
            try {
                return gson.fromJson(vcr, VocaRed.class);
            } catch (Exception ex) {
                return vc;
            }
        }
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setremote(boolean isFirstTime) {
        editor.putBoolean(ISREMOTE, isFirstTime);
        editor.commit();
    }

    public boolean isRemote() {
        return pref.getBoolean(ISREMOTE, true);
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


}
