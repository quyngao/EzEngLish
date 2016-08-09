package com.example.mypc.ezenglish.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Doc extends RealmObject {
    @PrimaryKey
    public int id;
    String type;
    String name;
    String location;
    @Ignore
    Lesson lesson;
}
