package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Schedule extends RealmModel {
    String name;
    String description;
    String numberday;
    Date daystart;
    RealmList<Alarm> alarms;
}
