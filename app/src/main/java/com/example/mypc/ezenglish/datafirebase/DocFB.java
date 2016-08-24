package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.Doc;

/**
 * Created by Quylt on 8/24/2016.
 */
public class DocFB {
    public int id;
    String type;
    String name;
    String location;

    public DocFB(int id, String type, String name, String location) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.location = location;
    }

    public DocFB(Doc d) {
        this.id = d.getId();
        this.type = d.getType();
        this.name = d.getName();
        this.location = d.getLocation();
    }

    public DocFB() {

    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
