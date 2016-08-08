package com.example.mypc.ezenglish.model;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Topic extends  RealmModel{
    String name;
    String img;
    String description;

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
