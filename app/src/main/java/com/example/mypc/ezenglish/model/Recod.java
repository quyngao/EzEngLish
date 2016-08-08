package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Recod extends RealmModel {
    String name;
    String location;
    Date recod;

    @Ignore
    Lesson lesson;
}
