package com.example.mypc.ezenglish.model;

import com.example.mypc.ezenglish.datafirebase.DocFB;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Doc extends RealmObject {
    @PrimaryKey
    public int id;
    String type;
    String name;
    String location;

    public Doc(DocFB doc) {
        this.id = doc.getId();
        this.type = doc.getType();
        this.name = doc.getName();
        this.location = doc.getLocation();
    }

    public Doc() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
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
