package com.example.mypc.ezenglish.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Voca extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String description;
    String img;
    String trans;
    @Ignore
    Lesson lesson;
}
