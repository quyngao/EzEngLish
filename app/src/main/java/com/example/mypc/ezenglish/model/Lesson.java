package com.example.mypc.ezenglish.model;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Lesson extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String img;
    String context;
    int time;
    Date start;
    int isrealy;
    RealmList<Recod> recods;
    RealmList<History> histories;
    RealmList<Voca> vocas;
    RealmList<MP3> all;

    @Ignore
    RealmList<Schedule> schedules;
    Doc doc;


    @Ignore
    List<MP3> ministore;
    @Ignore
    MP3 audio;
    @Ignore
    MP3 vocamp3;
    @Ignore
    Topic topic;
    @Ignore
    User user;


}
