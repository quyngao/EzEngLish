package com.example.mypc.ezenglish.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class History extends RealmObject {

    int type;
    long times;
    int longs;
    @Ignore
    Lesson lesson;

    public void setType(int type) {
        this.type = type;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public void setLongs(int longs) {
        this.longs = longs;
    }


    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }


    public int getType() {
        return type;
    }

    public long getTimes() {
        return times;
    }

    public int getLongs() {
        return longs;
    }


    public Lesson getLesson() {
        return lesson;
    }
}
