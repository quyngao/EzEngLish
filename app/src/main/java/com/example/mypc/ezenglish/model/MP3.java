package com.example.mypc.ezenglish.model;

import com.example.mypc.ezenglish.datafirebase.MP3FB;

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

    public MP3() {
    }

    public MP3(MP3FB mp3) {
        this.id = mp3.getId();
        this.name = mp3.getName();
        this.location = mp3.getLocation();
        this.type = mp3.getType();
        this.context = mp3.getContext();
    }

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

}
