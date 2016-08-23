package com.example.mypc.ezenglish.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class MP3 extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String location;
    int type;
    String context;
    @Ignore
    Lesson lesson;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public void setContext(String context) {
        this.context = context;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
