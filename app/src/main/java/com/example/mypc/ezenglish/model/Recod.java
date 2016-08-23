package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Recod extends RealmObject {

    String name;
    String location;
    String time;

    @Ignore
    Lesson lesson = new Lesson();

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
