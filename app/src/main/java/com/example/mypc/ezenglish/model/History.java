package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class History extends RealmModel {
    int type;
    long times;
    Date dates;

    @Ignore
    Lesson lesson;
}
