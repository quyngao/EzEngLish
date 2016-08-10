package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Schedule extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String description;
    String type;  //  type = 1 , bao hoc bai , type = 2 bao tu vung
    String numberday;
    Date daystart;
    RealmList<Alarm> alarms;
}
