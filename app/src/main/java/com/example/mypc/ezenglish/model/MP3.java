package com.example.mypc.ezenglish.model;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class MP3 extends  RealmModel{
    String name;
    String location;
    String type;
    String context;
    @Ignore
    Lesson lesson;
}
