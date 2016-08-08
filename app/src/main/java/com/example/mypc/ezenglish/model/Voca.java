package com.example.mypc.ezenglish.model;

import io.realm.annotations.Ignore;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Voca extends RealmModel {
    String name;
    String description;
    String img;
    String trans;
    @Ignore
    Lesson lesson;
}
