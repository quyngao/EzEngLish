package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Alarm extends RealmObject {
    @PrimaryKey
    public int id;
    String time; // gio
    int step;
    String type; // type = 0 ,1,2,3 listion, 4 voca
    String context;
    @Ignore
    Schedule schedule;
}
