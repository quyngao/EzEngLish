package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Recod extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String location;
    Date recod;

    @Ignore
    Lesson lesson;
}
