package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Alarm extends  RealmModel {
    int type;
    Date time;
    int step;
    String context;

    @Ignore
    Schedule schedule;
}
