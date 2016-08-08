package com.example.mypc.ezenglish.model;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Doc extends  RealmModel {
    String type;
    String name;
    String location;
    @Ignore
    Lesson lesson;
}
