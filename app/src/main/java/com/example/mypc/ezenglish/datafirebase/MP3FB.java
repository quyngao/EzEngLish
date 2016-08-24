package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.MP3;

/**
 * Created by Quylt on 8/24/2016.
 */
public class MP3FB {
    public int id;
    String name;
    String location;
    int type;
    String context;

    public MP3FB() {
    }

    public MP3FB(int id, String name, String location, int type, String context) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.context = context;
    }

    public MP3FB(MP3 mp3) {
        this.id = mp3.getId();
        this.name = mp3.getName();
        this.location = mp3.getLocation();
        this.type = mp3.getType();
        this.context = mp3.getContext();
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

    public String getContext() {
        return context;
    }
}
