package com.example.mypc.ezenglish.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Voca extends RealmObject implements Serializable {
    @PrimaryKey
    public int id;
    String name;
    String description;
    String img;
    String trans;
    @Ignore
    Lesson lesson;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTrans(String trans) {
        this.trans = trans;
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

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getTrans() {
        return trans;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
